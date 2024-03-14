package com.example.test.domain.use_cases

import com.example.test.Resource
import com.example.test.data.source.network.toUserDetails
import com.example.test.domain.Repository
import com.example.test.domain.models.UserDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: Repository) {
  operator fun invoke(): Flow<Resource<UserDetails>> = flow {
    try {
      emit(Resource.Loading<UserDetails>())
      val user = repository.getUser().body()!!.toUserDetails()
      emit(Resource.Success<UserDetails>(user))

    } catch (e: HttpException){
      emit(Resource.Error<UserDetails>(e.localizedMessage ?: "An unexpected error is occurred"))
    }
  }
}