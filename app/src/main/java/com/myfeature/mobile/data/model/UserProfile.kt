package com.myfeature.mobile.data.model

class UserProfile(
  val userId: String,
  val userName: String,
  val avatarUrl: String,
  val postsCount: Long,
  val followersCount: Long,
  val followingCount: Long
)