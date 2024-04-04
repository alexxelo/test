package com.example.test.ui.info_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test.Resource
import com.example.test.domain.use_cases.GetUserStreamUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserInfoViewModel @Inject constructor(
  private val getUserStreamUseCase: GetUserStreamUseCase,
  savedStateHandle: SavedStateHandle

) : ViewModel() {
  private val _state = mutableStateOf(UserInfoState())
  val state: State<UserInfoState> = _state

  init {
    savedStateHandle.get<String>("id")?.let { id ->
      Log.d("Debug", " user id = $id")
      getUserById(id)
    }
  }
  private fun getUserById(id:String){
    getUserStreamUseCase(id).onEach { result->
      when(result){
        is Resource.Success ->{
          _state.value = UserInfoState(user = result.data)
        }

        is Resource.Error -> {
          _state.value = UserInfoState(error = result.message ?: "An unexpected error occurred")
        }

        is Resource.Loading -> {
          _state.value = UserInfoState(isLoading = true)
        }
      }
    }.launchIn(viewModelScope)
  }
}