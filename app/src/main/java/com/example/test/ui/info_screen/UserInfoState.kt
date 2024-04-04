package com.example.test.ui.info_screen

import com.example.test.domain.models.UserDetails

data class UserInfoState (
  val isLoading: Boolean = false,
  val user: UserDetails? = null,
  val error: String = ""
)