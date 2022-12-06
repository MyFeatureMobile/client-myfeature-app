package com.myfeature.mobile.data.mapper

import com.myfeature.mobile.data.model.UserResponse
import com.myfeature.mobile.domain.model.UserProfile


fun UserResponse.toUserProfile(photoUrl: String): UserProfile {
  return UserProfile(
    userId = userId ?: 0,
    userName = username ?: "",
    avatarUrl = photoUrl,
    description = description ?: "",
    email = email,
    postsCount = postCount ?: 0,
    followersCount = 0,
    followingCount = 0
  )
}