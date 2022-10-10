package com.myfeature.mobile.ui.navigation

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myfeature.mobile.core.theme.AppTheme
import com.myfeature.mobile.ui.AppState
import com.myfeature.mobile.ui.beginner.BeginnerView
import com.myfeature.mobile.ui.beginner.login.LoginView
import com.myfeature.mobile.ui.beginner.login.RegisterView
import com.myfeature.mobile.ui.navigation.MainDestinations.BEGINNER
import com.myfeature.mobile.ui.navigation.MainDestinations.LOGIN_ROUTE
import com.myfeature.mobile.ui.navigation.MainDestinations.REGISTER_PART_ROUTE
import com.myfeature.mobile.ui.navigation.MainDestinations.WELCOME_ROUTE

@Composable
fun App() {
  AppTheme {
    val appState = rememberAppState()
    Scaffold(
      scaffoldState = appState.scaffoldState
    ) {
      NavHost(
        navController = appState.navController,
        startDestination = WELCOME_ROUTE
      ) {
        composable(WELCOME_ROUTE) {
          BeginnerView(
            onLogIn = { appState.navController.navigate(LOGIN_ROUTE) },
            onSignUp = { appState.navController.navigate(REGISTER_PART_ROUTE) })
        }
        composable(LOGIN_ROUTE) {
          LoginView()
        }
        composable(BEGINNER) {

        }
        composable(REGISTER_PART_ROUTE) {
          RegisterView()
        }
      }
    }
  }
}

@Composable
fun rememberAppState(
  scaffoldState: ScaffoldState = rememberScaffoldState(),
  navController: NavHostController = rememberNavController()
) = remember(scaffoldState, navController) {
  AppState(scaffoldState, navController)
}