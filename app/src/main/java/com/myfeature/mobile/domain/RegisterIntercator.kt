package com.myfeature.mobile.domain

import com.myfeature.mobile.domain.model.LoginData
import com.myfeature.mobile.domain.model.UserProfile
import com.myfeature.mobile.domain.repository.RegisterRepository

class RegisterInteractor(private val registerRepository: RegisterRepository) {

  suspend fun registerWithParams(userName: String, password: String): UserProfile {
    return registerRepository.register(LoginData(userName, password))
  }
}