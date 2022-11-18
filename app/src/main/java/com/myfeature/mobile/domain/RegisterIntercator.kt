package com.myfeature.mobile.domain

import com.myfeature.mobile.data.RegisterRepository
import com.myfeature.mobile.di.GraphDI

// TODO: ANDROID-17 Move all dependencies to constructor
class RegisterInteractor {

  private val registerRepository: RegisterRepository = GraphDI.registerRepository

  suspend fun registerAndSave(userName: String, password: String) {
    registerRepository.register(LoginData(userName, password))
  }
}