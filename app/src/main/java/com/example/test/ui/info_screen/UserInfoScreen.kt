package com.example.test.ui.info_screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.test.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInfoScreen(
  navController: NavController,
  modifier: Modifier = Modifier,
  viewModel: UserInfoViewModel = hiltViewModel(),
) {
  val state = viewModel.state.value
  state.user?.let { user ->

    Scaffold(
      topBar = {
        CenterAlignedTopAppBar(title = {
          Text(text = user.firstName + " " + user.lastName)
        },
          navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back),
                tint = colorResource(id = R.color.black)
              )
            }
          })
      },
    ) { padding ->

      Column(
        modifier = modifier
          .fillMaxSize()
          .padding(padding)
          .padding(16.dp)
      ) {
        AsyncImage(
          model = ImageRequest.Builder(context = LocalContext.current)
            .data(user.pictureLarge)
            .crossfade(true)
            .build(),
          contentDescription = stringResource(id = R.string.user_avatar),
          modifier = Modifier
            .size(360.dp)
            .padding(bottom = 16.dp),
          contentScale = ContentScale.Fit,

          error = painterResource(id = R.drawable.ic_broken_image),
          placeholder = painterResource(id = R.drawable.loading_img)
        )

        Text(text = "Name: " + user.firstName + " " + user.lastName)
        Text(text = "Gender: " + user.gender)
        Text(text = "Nationality: " + user.nat)
        Text(
          text = "Address: " + user.country + ", " + user.state +
              ", " + user.city + ", " + user.streetName + ", " + "${user.streetNumber}, " + user.postcode,
          modifier = Modifier.clickable { }
        )
        Text(text = "Phone: +" + user.phone, modifier = Modifier.clickable { })
        Text(text = "email: " + user.email, modifier = Modifier.clickable { })
        Text(text = "Registered date: " + user.registeredDate)

      }
    }
  }
}


@Preview
@Composable
fun UserInfoScreenPreview(modifier: Modifier = Modifier) {
  //UserInfoScreen(modifier = modifier)
}