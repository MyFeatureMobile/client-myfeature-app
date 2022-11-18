package com.myfeature.mobile.data

import com.myfeature.mobile.domain.LoginData
import kotlinx.coroutines.delay

class RegisterRepository {

  suspend fun register(loginData: LoginData) {
    delay(1000)
    // TODO: ANDROID-12
  }
}