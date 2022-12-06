package com.myfeature.mobile.data.model

import com.google.gson.annotations.SerializedName

class UserResponse(
  @SerializedName("user_id")
  val userId: Long,

  @SerializedName("username")
  val username: String? = null,

  @SerializedName("firstName")
  val firstName: String? = null,

  @SerializedName("lastName")
  val lastName: String? = null,

  @SerializedName("email")
  val email: String? = null,

  @SerializedName("password")
  val password: String? = null,

  @SerializedName("postCount")
  val postCount: Long = 0,

  @SerializedName("likeCount")
  val likeCount: Long = 0,

  @SerializedName("avatar")
  val avatar: Avatar? = null,

  @SerializedName("intro")
  val description: String = "",
)

class Avatar(
  @SerializedName("photo_id")
  val photoId: Long? = null,

  @SerializedName("url")
  val url: String? = null
)