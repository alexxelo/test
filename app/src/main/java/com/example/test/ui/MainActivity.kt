package com.example.test.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.test.ui.info_screen.UserInfoScreen
import com.example.test.ui.info_screen.UserInfoViewModel
import com.example.test.ui.theme.TestTheme
import com.example.test.ui.users_list_screen.UsersListScreen
import com.example.test.ui.users_list_screen.UsersListViewModel
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
  val ctx = LocalContext.current
  val navController = rememberNavController()

  val mainVM: UsersListViewModel = hiltViewModel()
  val userInfoVM:UserInfoViewModel = hiltViewModel()
  NavHost(
    navController = navController,
    startDestination = Screen.UsersListScreen.route
  ) {

    composable(route = Screen.UsersListScreen.route) {
      UsersListScreen(
        navController = navController,
        viewModel = mainVM
      )
    }
    composable(
      route = Screen.UserInfoScreen.route + "/{id}",
      arguments = listOf(navArgument("id") { type = NavType.StringType })
    ) {
      UserInfoScreen(
        navController =navController,
        viewModel = userInfoVM,
        onAddressClick = { latitude, longitude ->
          val gmmIntentUri = Uri.parse("geo:${latitude},$longitude")
          val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
          ctx.startActivity(mapIntent)
        },
        onEmailClick = {
          val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf(it)) // recipients
            putExtra(Intent.EXTRA_STREAM, Uri.parse("content://path/to/email/attachment"))
          }
          ctx.startActivity(emailIntent)
        },
        onPhoneClick = { phoneNumber ->
          val phoneIntent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
          }
          ctx.startActivity(phoneIntent)
        }
      )
    }
  }
}
