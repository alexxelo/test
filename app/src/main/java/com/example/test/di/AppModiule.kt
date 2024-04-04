package com.example.test.di

import android.content.Context
import androidx.room.Room
import com.example.test.data.RepositoryImpl
import com.example.test.data.source.local.UserDatabase
import com.example.test.data.source.local.UsersDataSource
import com.example.test.data.source.network.UserApi
import com.example.test.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
  @Provides
  @Singleton
  fun provideAppDatabase(@ApplicationContext context: Context): UserDatabase {
    return Room.databaseBuilder(
      context,
      UserDatabase::class.java, "users_database"
    ).fallbackToDestructiveMigration()
      .build()
  }

  @Provides
  @Singleton
  fun provideUsersDataSource(database: UserDatabase): UsersDataSource {
    return UsersDataSource(database.UserDao())
  }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
  @Provides
  @Singleton
  fun provideUserApi(): UserApi {
    val client = OkHttpClient.Builder()
      .addInterceptor { chain ->
        val request = chain.request().newBuilder()
          .build()
        chain.proceed(request)
      }
      .build()
    return Retrofit.Builder()
      .client(client)
      .baseUrl("https://randomuser.me")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(UserApi::class.java)
  }
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  @Singleton
  fun provideUserRepository(
    api: UserApi,
    db: UsersDataSource
  ): Repository {
    return RepositoryImpl(api, db)
  }
}