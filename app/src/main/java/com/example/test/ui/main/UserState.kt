package com.example.test.ui.main

import com.example.test.domain.models.UserDetails

data class UserState (
  val isLoading: Boolean = false,
  val user: List<UserDetails>? = null,
  val error: String = ""
)