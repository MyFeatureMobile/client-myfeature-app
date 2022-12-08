package com.myfeature.mobile.data.model

import com.google.gson.annotations.SerializedName

class CreatePostParams(
  @SerializedName("photo")
  val photoUrl: Long,
  @SerializedName("name")
  val description: String,
  @SerializedName("content")
  val code: String,
  @SerializedName("user")
  val userId: Long
)