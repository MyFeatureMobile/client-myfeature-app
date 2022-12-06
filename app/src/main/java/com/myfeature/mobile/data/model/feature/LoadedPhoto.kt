package com.myfeature.mobile.data.model.feature

import com.google.gson.annotations.SerializedName

class LoadedPhoto(
  @SerializedName("url")
  val url: String,

  @SerializedName("photo_id")
  val id: Long
)