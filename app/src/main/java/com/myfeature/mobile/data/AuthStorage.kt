package com.myfeature.mobile.data

import com.myfeature.mobile.data.model.UserResponse

object AuthStorage {

  var token: String = ""

  var userId: Long = 0L

  fun saveAuthData(data: UserResponse) {
    userId = data.userId ?: 0
  }

  fun clear() {
    userId = 0
  }
}