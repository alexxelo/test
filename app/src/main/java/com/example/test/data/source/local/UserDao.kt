package com.example.test.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
  @Query("SELECT * FROM users")
  fun getAll(): Flow<List<UserEntity>>
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(user:UserEntity)

  @Query("SELECT * FROM users WHERE uuid = :id")
  fun getPersonById(id: String): Flow<UserEntity>

  @Query("DELETE FROM  users")
  suspend fun deleteALl()

}