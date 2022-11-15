package com.myfeature.mobile.ui.home.post.model

data class PostModel(
  val id: String,
  val photoUrls: List<String>,
  val likesCount: Int,
  val liked: Boolean,
  val userOnPost: UserOnPost,
  val description: String,
  val codeLink: String
)