package com.myfeature.mobile.ui.beginner.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myfeature.mobile.core.theme.AppTheme
import com.myfeature.mobile.domain.LoginData
import com.myfeature.mobile.ui.beginner.BeginnerState
import com.myfeature.mobile.ui.beginner.BeginnerView
import com.myfeature.mobile.ui.beginner.loading.LoadingView
import com.myfeature.mobile.ui.beginner.login.LoginView
import com.myfeature.mobile.ui.beginner.navigation.BeginnerDestinations.FINISH_REGISTER_ROUTE
import com.myfeature.mobile.ui.beginner.navigation.BeginnerDestinations.LOADING
import com.myfeature.mobile.ui.beginner.navigation.BeginnerDestinations.LOGIN_ROUTE
import com.myfeature.mobile.ui.beginner.navigation.BeginnerDestinations.REGISTER_PART_ROUTE
import com.myfeature.mobile.ui.beginner.navigation.BeginnerDestinations.WELCOME_ROUTE
import com.myfeature.mobile.ui.beginner.register.FinishRegisterView
import com.myfeature.mobile.ui.beginner.register.RegisterView
import com.myfeature.mobile.ui.common.navigate
import com.myfeature.mobile.ui.common.requiredArg

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BeginApp(onAuthorizedAlready: () -> Unit, onLogin: (LoginData) -> Unit) {
  AppTheme {
    val appState = rememberBeginnerState()
    NavHost(
      navController = appState.navController, startDestination = LOADING
    ) {
      composable(LOADING) {
        LoadingView(onLoggedIn = onAuthorizedAlready, onNeedToAuth = { appState.navController.navigate(WELCOME_ROUTE) })
      }
      composable(WELCOME_ROUTE) {
        BeginnerView(onLogIn = { appState.navController.navigate(LOGIN_ROUTE) },
          onSignUp = { appState.navController.navigate(REGISTER_PART_ROUTE) })
      }
      composable(LOGIN_ROUTE) {
        LoginView(
          onLoggedIn = { onLogin.invoke(it) },
          onGoSignIn = { appState.navController.navigate(REGISTER_PART_ROUTE) },
          onRestoreAccess = {}
        )
      }
      composable(REGISTER_PART_ROUTE) {
        RegisterView(
          onAccountCreated = { userName, password ->
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

@Composable
fun rememberBeginnerState(navController: NavHostController = rememberNavController()) = remember(navController) {
  BeginnerState(navController)
}