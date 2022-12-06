package com.myfeature.mobile.data.model

import com.google.gson.annotations.SerializedName

class UpdateDescriptionParams(
  @SerializedName("description")
  val description: String
)