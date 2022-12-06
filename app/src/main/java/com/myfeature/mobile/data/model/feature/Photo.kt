package com.myfeature.mobile.data.model.feature

import com.google.gson.annotations.SerializedName

data class Photo(
  @SerializedName("photo_id") var photoId: Int? = null,
  @SerializedName("url") var url: String? = null,
  @SerializedName("created") var created: String? = null
)