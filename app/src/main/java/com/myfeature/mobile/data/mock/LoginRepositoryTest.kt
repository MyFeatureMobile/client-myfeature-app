package com.myfeature.mobile.data.mock

import com.myfeature.mobile.data.model.AuthParams
import com.myfeature.mobile.data.model.AuthResponse
import com.myfeature.mobile.data.model.UserResponse
import com.myfeature.mobile.domain.repository.LoginRepository
import timber.log.Timber

class LoginRepositoryTest : LoginRepository {

  override suspend fun authorize(data: AuthParams): UserResponse? {
    return try {
      // TODO: Use real data with retrofit
      UserResponse(1)
    } catch (e: Exception) {
      Timber.e(e, "Error on trying to authorize")
      return null
    }
  }
}