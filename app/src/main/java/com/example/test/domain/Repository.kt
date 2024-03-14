package com.example.test.domain

import com.example.test.data.source.local.UserEntity
import com.example.test.data.source.network.UserResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
  suspend fun getPerson():Response<UserResponse>


  //db
  fun getPersonsStream():Flow<List<UserEntity>>
  fun getPersonStream(id:Int):Flow<UserEntity>


}