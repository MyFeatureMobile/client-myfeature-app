package com.myfeature.mobile.data.model

import com.google.gson.annotations.SerializedName

class AuthResponse(
  @SerializedName("user_id")
  val userId: String
)