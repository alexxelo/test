package com.example.test.data.source

import com.example.test.data.source.local.UserEntity
import kotlinx.coroutines.flow.Flow


interface DataSource<T> {
  fun getAll(): Flow<List<T>>
  suspend fun insert(userEntity: UserEntity)
  fun getPersonById(id: String): Flow<T>
}