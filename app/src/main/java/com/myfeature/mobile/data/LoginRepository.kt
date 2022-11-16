package com.myfeature.mobile.data

import com.myfeature.mobile.data.model.AuthResponse
import com.myfeature.mobile.data.model.AuthParams
import timber.log.Timber

class LoginRepository {

  suspend fun authorize(data: AuthParams): AuthResponse? {
    return try {
      // TODO: Use real data with retrofit
      AuthResponse("1234567", "1234567")
    } catch (e: Exception) {
      Timber.e(e, "Error on trying to authorize")
      return null
    }
  }
}