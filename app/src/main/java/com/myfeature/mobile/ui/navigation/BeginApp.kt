package com.myfeature.mobile.ui.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myfeature.mobile.core.theme.AppTheme
import com.myfeature.mobile.domain.LoginData
import com.myfeature.mobile.ui.AppState
import com.myfeature.mobile.ui.beginner.BeginnerView
import com.myfeature.mobile.ui.beginner.login.FinishRegisterView
import com.myfeature.mobile.ui.beginner.login.LoginView
import com.myfeature.mobile.ui.beginner.login.RegisterView
import com.myfeature.mobile.ui.common.navigate
import com.myfeature.mobile.ui.common.requiredArg
import com.myfeature.mobile.ui.navigation.BeginnerDestinations.FINISH_REGISTER_ROUTE
import com.myfeature.mobile.ui.navigation.BeginnerDestinations.LOGIN_ROUTE
import com.myfeature.mobile.ui.navigation.BeginnerDestinations.REGISTER_PART_ROUTE
import com.myfeature.mobile.ui.navigation.BeginnerDestinations.WELCOME_ROUTE

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BeginApp(onLogin: (LoginData) -> Unit) {
  AppTheme {
    val appState = rememberAppState()
    Scaffold(
      scaffoldState = appState.scaffoldState,
    ) { padding ->
      NavHost(
        navController = appState.navController, startDestination = WELCOME_ROUTE, modifier = Modifier.padding(padding)
      ) {
        composable(WELCOME_ROUTE) {
          BeginnerView(onLogIn = { appState.navController.navigate(LOGIN_ROUTE) },
            onSignUp = { appState.navController.navigate(REGISTER_PART_ROUTE) })
        }
        composable(LOGIN_ROUTE) {
          LoginView(onLogInClick = {},
            onGoSignIn = { appState.navController.navigate(REGISTER_PART_ROUTE) },
            onRestoreAccess = {})
        }
        composable(REGISTER_PART_ROUTE) {
          RegisterView(onInputFinished = { userName, password ->
            // TODO: ANDROID-8
            val data = LoginData(userName, password)
            appState.navController.navigate(FINISH_REGISTER_ROUTE, "data" to data)
          })
        }
        composable(
          route = FINISH_REGISTER_ROUTE
        ) {
          val data = it.requiredArg<LoginData>("data")
          FinishRegisterView(userName = data.userName, onCompleteRegister = { onLogin.invoke(data) })
        }
      }
    }
  }
}

@Composable
fun rememberAppState(
  scaffoldState: ScaffoldState = rememberScaffoldState(), navController: NavHostController = rememberNavController()
) = remember(scaffoldState, navController) {
  AppState(scaffoldState, navController)
}