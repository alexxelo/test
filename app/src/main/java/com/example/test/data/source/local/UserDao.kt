package com.example.test.data.source.local

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
  @Query("SELECT * FROM users")
  fun getAll(): Flow<List<UserEntity>>

  @Query("SELECT * FROM users WHERE uuid = :id")
  fun getPersonById(id: String): Flow<UserEntity>

}