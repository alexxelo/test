package com.example.test.ui

sealed class Screen(val route: String) {
  data object UsersListScreen : Screen("users_list_screen")
  data object UserInfoScreen : Screen("user_info_screen")

}