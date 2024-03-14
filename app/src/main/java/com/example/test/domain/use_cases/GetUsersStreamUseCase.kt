package com.example.test.domain.use_cases

import com.example.test.Resource
import com.example.test.data.source.local.toUserDetails
import com.example.test.domain.Repository
import com.example.test.domain.models.UserDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUsersStreamUseCase @Inject constructor(private val repository: Repository) {
  operator fun invoke(): Flow<Resource<List<UserDetails>>> = flow {
    try {
      emit(Resource.Loading<List<UserDetails>>())
      val usersList = mutableListOf<UserDetails>()
      repository.getUsersStream().collect { listOfUsers ->
        usersList.addAll(listOfUsers.map { it.toUserDetails() })
        emit(Resource.Success<List<UserDetails>>(usersList))
      }

    } catch (e: IOException) {
      emit(Resource.Error<List<UserDetails>>(e.localizedMessage ?: "Couldn't reach. "))

    }
  }
}