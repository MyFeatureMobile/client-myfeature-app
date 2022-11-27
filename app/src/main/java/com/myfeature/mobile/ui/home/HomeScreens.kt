package com.myfeature.mobile.ui.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.myfeature.mobile.R
import com.myfeature.mobile.ui.home.HomeScreens.PROFILE
import com.myfeature.mobile.ui.home.MainDestinations.MAIN_ROUTE

internal val DEFAULT_SCREEN = HomeScreens.FOLLOWING

enum class HomeScreens(
  @StringRes val title: Int,
  val icon: ImageVector,
  val route: String
) {
  FOLLOWING(
    R.string.favourites_bottom_title,
    Icons.Outlined.FavoriteBorder,
    "$MAIN_ROUTE/${MainDestinations.FOLLOWED}"
  ),
  TIMELINE(R.string.timeline_bottom_title, Icons.Outlined.Home, "$MAIN_ROUTE/${MainDestinations.TIMELINE}"),
  PROFILE(R.string.home_profile, Icons.Outlined.AccountCircle, "$MAIN_ROUTE/${MainDestinations.PROFILE}"),
}

val PROFILE_EDIT = "${PROFILE.route}/edit"