package com.myfeature.mobile.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myfeature.mobile.ui.home.HomeScreens.FOLLOWING
import com.myfeature.mobile.ui.home.HomeScreens.PROFILE
import com.myfeature.mobile.ui.home.HomeScreens.TIMELINE
import com.myfeature.mobile.ui.home.MainDestinations.MAIN_POST
import com.myfeature.mobile.ui.home.post.PostView
import com.myfeature.mobile.ui.home.profile.ProfileView
import com.myfeature.mobile.ui.home.timeline.TimelineView

@Composable
fun HomeNavGraph(navController: NavHostController, modifier: Modifier = Modifier, onLogOut: () -> Unit) {
  NavHost(
    navController = navController,
    route = MainDestinations.MAIN_ROUTE,
    startDestination = DEFAULT_SCREEN.route
  ) {
    composable(route = FOLLOWING.route) {
      TimelineView(
        modifier = modifier,
        label = "Following",
        onPostClick = { postId -> navController.navigate(MAIN_POST) /* TODO: Send param */ }
      )
    }
    composable(route = TIMELINE.route) {
      TimelineView(modifier)
    }
    composable(route = PROFILE.route) {
      ProfileView(modifier, onLogOut = onLogOut)
    }
    composable(route = MAIN_POST) {
      PostView(modifier)
    }
  }
}

sealed class DetailsScreen(val route: String) {
  object Information : DetailsScreen(route = "INFORMATION")
  object Overview : DetailsScreen(route = "OVERVIEW")
}