package com.myfeature.mobile.domain.model

class UserProfile(
  val userId: Long,
  val userName: String,
  val avatarUrl: String? = null,
  val description: String = "",
  val email: String? = null,
  val postsCount: Long = 0,
  val followersCount: Long = 0,
  val followingCount: Long = 0
)