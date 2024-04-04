package com.example.test.data

import com.example.test.data.source.local.UserEntity
import com.example.test.data.source.local.UsersDataSource
import com.example.test.data.source.network.UserApi
import com.example.test.data.source.network.UserResponse
import com.example.test.domain.Repository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
  private val userApi: UserApi,
  private val userDs: UsersDataSource
):Repository {
  override suspend fun getUser(): Response<UserResponse> {
    return userApi.getUser()
  }

  override suspend fun insert(userEntity: UserEntity) {
    userDs.insert(userEntity)
  }

  override fun getUsersStream(): Flow<List<UserEntity>> {
    return userDs.getAll()
  }

  override fun getUserStream(id: String): Flow<UserEntity> {
    return userDs.getPersonById(id)
  }
}