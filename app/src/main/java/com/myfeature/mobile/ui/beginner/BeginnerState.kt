package com.myfeature.mobile.ui.beginner

import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController

@Stable
class BeginnerState(
    val navController: NavHostController
) {

  val currentRoute: String?
    get() = navController.currentDestination?.route
}