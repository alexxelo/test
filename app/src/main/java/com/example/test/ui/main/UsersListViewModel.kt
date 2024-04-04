package com.example.test.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.Resource
import com.example.test.domain.models.toUserEntity
import com.example.test.domain.use_cases.AddUserToDBUseCase
import com.example.test.domain.use_cases.GetUserUseCase
import com.example.test.domain.use_cases.GetUsersStreamUseCase
import com.example.test.ui.info_screen.UserInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
  private val getUserUseCase: GetUserUseCase,
  private val addUserToDBUseCase: AddUserToDBUseCase,
  private val getUsersStreamUseCase: GetUsersStreamUseCase
) : ViewModel() {

  private val _state = mutableStateOf(UserState())
  val state: State<UserState> = _state

  private val _state1 = mutableStateOf(UserInfoState())
  val state1: State<UserInfoState> = _state1

  init {
    getUser()
    getUsersStream()
  }

  //database
  private fun getUsersStream() {
    getUsersStreamUseCase().onEach { result ->
      when (result) {
        is Resource.Success -> {
          _state.value = UserState(user = result.data)
        }

        is Resource.Error -> {
          _state.value = UserState(error = result.message ?: "An unexpected error occured")
        }

        is Resource.Loading -> {
          _state.value = UserState(isLoading = true)
        }
      }
    }.launchIn(viewModelScope)
  }

  //api
  private fun getUser() {
    getUserUseCase().onEach { result ->
      when (result) {
        is Resource.Success -> {
          addUserToDBUseCase(result.data!!.toUserEntity())
          _state1.value = UserInfoState(user = result.data)
        }

        is Resource.Error -> {
          _state1.value = UserInfoState(error = result.message ?: "An unexpected error occured")
        }

        is Resource.Loading -> {
          _state1.value = UserInfoState(isLoading = true)
        }
      }
    }.launchIn(viewModelScope)
  }

}