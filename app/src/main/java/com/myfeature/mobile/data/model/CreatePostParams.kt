package com.myfeature.mobile.data.model

import com.google.gson.annotations.SerializedName

class CreatePostParams(
  @SerializedName("photo")
  val photoUrl: List<String>,
  @SerializedName("description")
  val description: String,
  @SerializedName("link")
  val githubLink: String,
  @SerializedName("content")
  val code: String
)