package com.myfeature.mobile.data

import com.myfeature.mobile.data.model.AuthResponse

object AuthStorage {

  var token: String = ""

  var userId: String = ""

  fun saveAuthData(data: AuthResponse) {
    token = data.token
    userId = data.userId
  }

  fun clear() {
    token = ""
    userId = ""
  }
}