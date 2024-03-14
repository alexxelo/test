package com.example.test.data

import com.example.test.data.source.local.UserEntity
import com.example.test.data.source.network.UserResponse
import com.example.test.domain.Repository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class RepositoryImpl():Repository {
  override suspend fun getPerson(): Response<UserResponse> {
    TODO("Not yet implemented")
  }

  override fun getPersonsStream(): Flow<List<UserEntity>> {
    TODO("Not yet implemented")
  }

  override fun getPersonStream(id: Int): Flow<UserEntity> {
    TODO("Not yet implemented")
  }
}