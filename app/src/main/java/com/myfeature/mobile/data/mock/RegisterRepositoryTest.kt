package com.myfeature.mobile.data.mock

import com.myfeature.mobile.domain.model.LoginData
import com.myfeature.mobile.domain.repository.RegisterRepository
import kotlinx.coroutines.delay

class RegisterRepositoryTest : RegisterRepository {

  override suspend fun register(loginData: LoginData) {
    delay(1000)
    // TODO: ANDROID-12
  }
}