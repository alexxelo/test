package com.example.test.domain

import com.example.test.data.source.local.UserEntity
import com.example.test.data.source.network.UserResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface Repository {
  suspend fun getUser():Response<UserResponse>
  suspend fun insert(userEntity: UserEntity)
  //db
  fun getUsersStream():Flow<List<UserEntity>>
  fun getUserStream(id:String):Flow<UserEntity>


}