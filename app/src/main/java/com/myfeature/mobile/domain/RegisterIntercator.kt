package com.myfeature.mobile.domain

import com.myfeature.mobile.domain.model.LoginData
import com.myfeature.mobile.domain.repository.RegisterRepository

// TODO: ANDROID-17 Move all dependencies to constructor
class RegisterInteractor(private val registerRepository: RegisterRepository) {

  suspend fun registerAndSave(userName: String, password: String) {
    registerRepository.register(LoginData(userName, password))
  }
}