package com.example.test.ui.users_list_screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.test.R
import com.example.test.domain.models.UserDetails
import com.example.test.ui.Screen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersListScreen(
  navController: NavController,
  modifier: Modifier = Modifier,
  viewModel: UsersListViewModel
) {
  val state = viewModel.state.collectAsState()

  val isLoading = viewModel.isLoading.collectAsState()
  val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading.value)

  Scaffold(
    topBar = { CenterAlignedTopAppBar(title = { Text(text = "Users") })}
  ) {
    SwipeRefresh(
      state = swipeRefreshState,
      onRefresh = viewModel::loading
    ) {
      Box(modifier = Modifier.padding(it)) {

        LazyColumn(modifier = modifier) {
          state.value.user?.let {
            items(it) { user ->
              UserInfo(
                user = user,
                onUserCLick = { navController.navigate(Screen.UserInfoScreen.route + "/${user.uuid}") })
            }
          }
        }
      }
    }
  }
}

@Composable
fun UserInfo(
  modifier: Modifier = Modifier,
  user: UserDetails,
  onUserCLick: () -> Unit
) {

  Card(modifier = modifier
    .fillMaxWidth()
    .padding(16.dp)
    .clickable { onUserCLick() }) {
    Row(modifier = modifier) {
      AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
          .data(user.pictureMedium)
          .crossfade(true)
          .build(),
        contentDescription = stringResource(id = R.string.user_avatar),
        modifier = Modifier
          .size(120.dp)
          .padding(all = 16.dp),
        //.weight(0.40f),
        contentScale = ContentScale.Fit,

        error = painterResource(id = R.drawable.ic_broken_image),
        placeholder = painterResource(id = R.drawable.loading_img)
      )
      Column(modifier = modifier.padding(top = 16.dp, start = 16.dp)) {
        Text(text = user.firstName + " " + user.lastName)
        Text(text = user.city)
        Text(text = "+ " + user.phone)
      }
    }
  }
}

@Preview
@Composable
fun MainScreenPreview(modifier: Modifier = Modifier) {
  //UsersListScreen(modifier = modifier)
}