package com.example.test.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.test.ui.info_screen.UserInfoScreen
import com.example.test.ui.main.UsersListScreen
import com.example.test.ui.theme.TestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      TestTheme {
        // A surface container using the 'background' color from the theme
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          Navigation()
        }
      }
    }
  }
}

@Composable
fun Navigation() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = Screen.UsersListScreen.route
  ) {

    composable(route = Screen.UsersListScreen.route) {
      UsersListScreen(navController)
    }
    composable(
      route = Screen.UserInfoScreen.route + "/{id}",
      arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) {
      UserInfoScreen(navController)
    }
  }
}