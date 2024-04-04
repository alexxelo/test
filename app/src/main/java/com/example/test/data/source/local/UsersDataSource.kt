package com.example.test.data.source.local

import com.example.test.data.source.DataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersDataSource @Inject constructor(private val dao: UserDao) : DataSource<UserEntity> {
  override fun getAll(): Flow<List<UserEntity>> {
    return dao.getAll()
  }

  override suspend fun insert(userEntity: UserEntity) {
    dao.insert(userEntity)
  }

  override fun getPersonById(id: String): Flow<UserEntity> {
    return dao.getPersonById(id)
  }
}