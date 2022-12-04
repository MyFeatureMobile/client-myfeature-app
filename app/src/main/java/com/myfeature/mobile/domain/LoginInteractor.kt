package com.myfeature.mobile.domain

import com.myfeature.mobile.data.AuthStorage
import com.myfeature.mobile.data.LoginDataLocalStorage
import com.myfeature.mobile.data.model.AuthParams
import com.myfeature.mobile.data.model.AuthResponse
import com.myfeature.mobile.data.model.StoredLoginData
import com.myfeature.mobile.domain.repository.LoginRepository
import org.koin.java.KoinJavaComponent.get

class LoginInteractor(
  private val loginLocalStorage: LoginDataLocalStorage = get(LoginDataLocalStorage::class.java),
  private val loginRepository: LoginRepository = get(LoginRepository::class.java)
) {

  suspend fun savedAuthData(): StoredLoginData? {
    return loginLocalStorage.getDataFromStorage()
  }

  suspend fun authorize(userName: String, password: String): AuthResponse? {
    return loginRepository.authorize(AuthParams(userName, password))
  }

  suspend fun saveData(userName: String, password: String) {
    return loginLocalStorage.saveData(StoredLoginData(userName, password))
  }

  suspend fun logOut() {
    AuthStorage.clear()
    loginLocalStorage.clear()
  }
}