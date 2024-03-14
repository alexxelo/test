package com.example.test.domain.use_cases

import com.example.test.Resource
import com.example.test.data.source.local.toUserDetails
import com.example.test.domain.Repository
import com.example.test.domain.models.UserDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUserStreamUseCase @Inject constructor(private val repository: Repository) {
  operator fun invoke(id: String): Flow<Resource<UserDetails>> = flow {
    try {
      emit(Resource.Loading<UserDetails>())
      repository.getUserStream(id).collect {
        emit(Resource.Success(it.toUserDetails()))
      }

    } catch (e: IOException) {
      emit(Resource.Error<UserDetails>(e.localizedMessage ?: "Couldn't reach. "))

    }
  }
}