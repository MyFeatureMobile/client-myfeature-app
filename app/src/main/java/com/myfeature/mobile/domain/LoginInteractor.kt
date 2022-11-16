package com.myfeature.mobile.domain

import com.myfeature.mobile.data.model.AuthParams
import com.myfeature.mobile.data.model.AuthResponse
import com.myfeature.mobile.data.model.StoredLoginData
import com.myfeature.mobile.di.GraphDI

class LoginInteractor {

  private val loginLocalStorage = GraphDI.loginDataLocalStorage

  private val loginRepository = GraphDI.loginRepository

  suspend fun savedAuthData(): StoredLoginData? {
    return loginLocalStorage.getDataFromStorage()
  }

  suspend fun authorize(userName: String, password: String): AuthResponse? {
    return loginRepository.authorize(AuthParams(userName, password))
  }

  suspend fun saveData(userName: String, password: String) {
    return loginLocalStorage.saveData(StoredLoginData(userName, password))
  }
}