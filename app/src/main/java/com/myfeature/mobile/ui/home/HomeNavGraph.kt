package com.myfeature.mobile.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myfeature.mobile.ui.home.HomeScreens.FOLLOWING
import com.myfeature.mobile.ui.home.HomeScreens.PROFILE
import com.myfeature.mobile.ui.home.HomeScreens.TIMELINE
import com.myfeature.mobile.ui.home.following.FollowingTimelineView
import com.myfeature.mobile.ui.home.profile.ProfileView
import com.myfeature.mobile.ui.home.timeline.TimelineView

@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
  NavHost(
    navController = navController,
    route = MainDestinations.MAIN_ROUTE,
    startDestination = DEFAULT_SCREEN.route
  ) {
    composable(route = FOLLOWING.route) {
      FollowingTimelineView(modifier)
    }
    composable(route = TIMELINE.route) {
      TimelineView(modifier)
    }
    composable(route = PROFILE.route) {
      ProfileView(modifier)
    }
    detailsNavGraph(navController = navController)
  }
}

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
//  navigation(
//    route = Graph.DETAILS,
//    startDestination = DetailsScreen.Information.route
//  ) {
//    composable(route = DetailsScreen.Information.route) {
//      ScreenContent(name = DetailsScreen.Information.route) {
//        navController.navigate(DetailsScreen.Overview.route)
//      }
//    }
//    composable(route = DetailsScreen.Overview.route) {
//      ScreenContent(name = DetailsScreen.Overview.route) {
//        navController.popBackStack(
//          route = DetailsScreen.Information.route,
//          inclusive = false
//        )
//      }
//    }
//  }
}

sealed class DetailsScreen(val route: String) {
  object Information : DetailsScreen(route = "INFORMATION")
  object Overview : DetailsScreen(route = "OVERVIEW")
}