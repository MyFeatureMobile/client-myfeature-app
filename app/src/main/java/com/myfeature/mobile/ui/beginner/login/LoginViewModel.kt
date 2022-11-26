package com.myfeature.mobile.ui.beginner.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfeature.mobile.data.AuthStorage
import com.myfeature.mobile.di.GraphDI
import com.myfeature.mobile.domain.LoginData
import com.myfeature.mobile.domain.LoginInteractor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

  private val loginInteractor: LoginInteractor by lazy { GraphDI.loginInteractor }

  fun logIn(userName: String, password: String, onLogIn: (LoginData) -> Unit, onError: () -> Unit) {
    val exceptionHandler = CoroutineExceptionHandler { _, _ -> onError.invoke() }
    viewModelScope.launch(exceptionHandler) {
      val authData = loginInteractor.authorize(userName, password) ?: throw NullPointerException()
      AuthStorage.saveAuthData(authData)
      loginInteractor.saveData(userName, password)
      onLogIn.invoke(LoginData(userName, password))
    }
  }
}