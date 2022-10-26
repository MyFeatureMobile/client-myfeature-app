package com.myfeature.mobile.ui.bottom

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.myfeature.mobile.R
import com.myfeature.mobile.ui.main.MainDestinations.MAIN_ROUTE

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
  FAVOURITES(R.string.favourites_bottom_title, Icons.Outlined.FavoriteBorder, "$MAIN_ROUTE/${FAVOURITES}"),
  TIMELINE(R.string.timeline_bottom_title, Icons.Outlined.Home, "$MAIN_ROUTE/timeline"),
  PROFILE(R.string.home_profile, Icons.Outlined.AccountCircle, "$MAIN_ROUTE/profile"),
}