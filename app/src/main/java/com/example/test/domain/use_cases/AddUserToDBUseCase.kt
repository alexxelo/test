package com.example.test.domain.use_cases

import com.example.test.data.source.local.UserEntity
import com.example.test.domain.Repository
import javax.inject.Inject

class AddUserToDBUseCase @Inject constructor(private val repository: Repository) {
  suspend operator fun invoke(userEntity: UserEntity) {
    repository.insert(userEntity)
  }
}