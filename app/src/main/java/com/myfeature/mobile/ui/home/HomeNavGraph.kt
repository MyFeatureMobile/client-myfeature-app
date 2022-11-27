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
import com.myfeature.mobile.ui.home.post.create.PostCreateView
import com.myfeature.mobile.ui.home.profile.ProfileView
import com.myfeature.mobile.ui.home.profile.edit.ProfileEditView
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
        onAddPostClick = { navController.navigate(POST_ADD) },
        onPostClick = { postId -> navController.navigate(MAIN_POST) /* TODO: Send param */ }
      )
    }
    composable(route = POST_ADD) {
      PostCreateView(modifier = modifier, onCompleted = { navController.navigate(PROFILE.route) })
    }
    composable(route = TIMELINE.route) {
      TimelineView(modifier)
    }
    composable(route = PROFILE.route) {
      ProfileView(modifier, onLogOut = onLogOut) { navController.navigate(PROFILE_EDIT) }
    }
    composable(route = PROFILE_EDIT) {
      ProfileEditView(
        modifier = modifier,
        onUpdated = { navController.navigate(PROFILE.route) },
        onCancel = { navController.navigate(PROFILE.route) }
      )
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