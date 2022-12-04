package com.myfeature.mobile.data

import com.myfeature.mobile.data.model.AuthParams
import com.myfeature.mobile.data.model.AuthResponse
import com.myfeature.mobile.domain.repository.LoginRepository

class LoginRepositoryImpl: LoginRepository {

  override suspend fun authorize(data: AuthParams): AuthResponse? {
    throw NotImplementedError()
  }
}