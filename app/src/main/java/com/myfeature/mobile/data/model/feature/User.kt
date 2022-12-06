package com.myfeature.mobile.data.model.feature

import com.google.gson.annotations.SerializedName
import com.myfeature.mobile.data.model.Avatar

data class User(
  @SerializedName("user_id") var userId: Int? = null,
  @SerializedName("username") var username: String? = null,
  @SerializedName("firstName") var firstName: String? = null,
  @SerializedName("lastName") var lastName: String? = null,
  @SerializedName("email") var email: String? = null,
  @SerializedName("password") var password: String? = null,
  @SerializedName("postCount") var postCount: Int? = null,
  @SerializedName("likeCount") var likeCount: Int? = null,
  @SerializedName("avatar") var avatar: Avatar? = Avatar(),
  @SerializedName("intro") var intro: String? = null,
  @SerializedName("created") var created: String? = null
)