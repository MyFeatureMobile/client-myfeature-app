package com.myfeature.mobile.ui.home.profile.model

import androidx.annotation.StringRes
import com.myfeature.mobile.R

enum class Category(@StringRes val title: Int) {
  POSTS(R.string.profile_category_posts),
  FOLLOWERS(R.string.profile_category_followers),
  FOLLOWING(R.string.profile_category_following),
}