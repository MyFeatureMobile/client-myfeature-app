package com.myfeature.mobile.ui.home

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.myfeature.mobile.core.utils.navigate
import com.myfeature.mobile.core.utils.requiredArg
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
    composable(route = TIMELINE.route) {
      TimelineView(
        modifier = modifier,
        label = "Following",
        onAddPostClick = { navController.navigate(POST_ADD) },
        onPostClick = { postId ->
          val post = PostId(postId)
          navController.navigate(MAIN_POST, "id" to post)
        }
      )
    }
    composable(route = POST_ADD) {
      PostCreateView(modifier = modifier, onCompleted = { navController.navigate(PROFILE.route) })
    }
    composable(route = FOLLOWING.route) {
      TimelineView(
        modifier = modifier,
        onAddPostClick = { navController.navigate(POST_ADD) },
        onPostClick = { postId ->
          val post = PostId(postId)
          navController.navigate(MAIN_POST, "id" to post)
        })
    }
    composable(route = PROFILE.route) {
      ProfileView(modifier, onLogOut = onLogOut) { navController.navigate(PROFILE_EDIT) }
    }
    composable(route = PROFILE_EDIT) {
      ProfileEditView(
        modifier = modifier,
        onUpdated = { navController.navigate(PROFILE.route) },
        onCancel = { navController.navigate(PROFILE.route) },
        onPostClick = { postId -> navController.navigate(MAIN_POST, "id" to PostId(postId)) }
      )
    }
    composable(route = MAIN_POST) {
      val data = it.requiredArg<PostId>("id")
      PostView(modifier, data.id)
    }
  }
}

sealed class DetailsScreen(val route: String) {
  object Information : DetailsScreen(route = "INFORMATION")
  object Overview : DetailsScreen(route = "OVERVIEW")
}

class PostId(val id: Long) : Parcelable {
  constructor(parcel: Parcel) : this(parcel.readLong()) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeLong(id)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Creator<PostId> {
    override fun createFromParcel(parcel: Parcel): PostId {
      return PostId(parcel)
    }

    override fun newArray(size: Int): Array<PostId?> {
      return arrayOfNulls(size)
    }
  }
}