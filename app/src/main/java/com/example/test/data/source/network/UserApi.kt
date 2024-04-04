package com.example.test.data.source.network

import retrofit2.Response
import retrofit2.http.GET

interface UserApi {
  @GET("/api/")
  suspend fun getUser():Response<UserResponse>
}