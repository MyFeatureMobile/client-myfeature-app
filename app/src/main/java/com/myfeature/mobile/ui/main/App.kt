package com.myfeature.mobile.ui.main

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.myfeature.mobile.core.theme.AppTheme
import com.myfeature.mobile.ui.bottom.HomeSections

@Composable
fun App(onLogOut: () -> Unit) {
  AppTheme {
    val appState = rememberAppState()

    Scaffold(
      scaffoldState = appState.scaffoldState,
      bottomBar = {
        BottomAppBar(
        ) {
          HomeSections.values().forEach {
            IconButton(onClick = {}) {
              Icon(imageVector = it.icon, contentDescription = stringResource(it.title))
            }
          }
        }
      }
    ) { padding ->
      Text("Hello!")
    }
  }
}

@Composable
fun rememberAppState(
  scaffoldState: ScaffoldState = rememberScaffoldState(), navController: NavHostController = rememberNavController()
) = remember(scaffoldState, navController) {
  AppState(scaffoldState, navController)
}