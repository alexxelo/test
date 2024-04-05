package com.example.test.ui.users_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.Resource
import com.example.test.domain.models.toUserEntity
import com.example.test.domain.use_cases.AddUserToDBUseCase
import com.example.test.domain.use_cases.DeleteAllUsersUseCase
import com.example.test.domain.use_cases.GetUserUseCase
import com.example.test.domain.use_cases.GetUsersStreamUseCase
import com.example.test.ui.info_screen.UserInfoState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
  private val getUserUseCase: GetUserUseCase,
  private val addUserToDBUseCase: AddUserToDBUseCase,
  private val getUsersStreamUseCase: GetUsersStreamUseCase,
  private val deleteAllUsersUseCase: DeleteAllUsersUseCase
) : ViewModel() {

  private val _state = MutableStateFlow(UserState())
  val state: StateFlow<UserState> = _state.asStateFlow()

  private val _state1 = MutableStateFlow(UserInfoState())
  val state1: StateFlow<UserInfoState> = _state1.asStateFlow()


  private val _isLoading = MutableStateFlow(false)
  val isLoading = _isLoading.asStateFlow()

  fun loading(){
    viewModelScope.launch {
      _isLoading.value = true

      deleteAllUsersUseCase()
      delay(3000)
      _isLoading.value = false
      getUser()
      getUsersStream()
    }
  }
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
         // _state1.value = UserInfoState(user = result.data)
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