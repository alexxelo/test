package com.example.test.domain.use_cases

import com.example.test.domain.Repository
import javax.inject.Inject

class DeleteAllUsersUseCase @Inject constructor(private val repository: Repository){

  suspend operator fun invoke(){
    repository.deleteAll()
  }
}