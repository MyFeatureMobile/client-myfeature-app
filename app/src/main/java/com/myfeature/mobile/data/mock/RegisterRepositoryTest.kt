package com.myfeature.mobile.data.mock

import com.myfeature.mobile.data.mock.TestData.userProfile
import com.myfeature.mobile.domain.model.LoginData
import com.myfeature.mobile.domain.model.UserProfile
import com.myfeature.mobile.domain.repository.RegisterRepository
import kotlinx.coroutines.delay

class RegisterRepositoryTest : RegisterRepository {

  override suspend fun register(loginData: LoginData): UserProfile {
    delay(1000)
    return userProfile
  }
}