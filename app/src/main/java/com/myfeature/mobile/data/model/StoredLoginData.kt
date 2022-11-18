package com.myfeature.mobile.data.model

import com.google.gson.annotations.SerializedName

data class StoredLoginData(
  @SerializedName("username")
  val username: String,
  @SerializedName("password")
  val password: String
)