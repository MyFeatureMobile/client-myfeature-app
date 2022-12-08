package com.myfeature.mobile.ui.beginner.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfeature.mobile.core.coroutines.AppDispatchers
import com.myfeature.mobile.data.AuthStorage
import com.myfeature.mobile.domain.LoginInteractor
import com.myfeature.mobile.domain.RegisterInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class RegisterViewModel(
  private val appDispatchers: AppDispatchers,
  private val loginInteractor: LoginInteractor,
  private val registerInteractor: RegisterInteractor
) : ViewModel() {

  val registerState: Flow<RegisterState>
    get() = _registerState

  private val _registerState = MutableStateFlow(RegisterState("", "", requestUserName = true, loading = false))

  fun setUserName(userName: String) {
    _registerState.value = _registerState.value.copy(userName = userName, requestUserName = false)
  }

  fun setPassword(password: String) {
    _registerState.value = _registerState.value.copy(password = password)
  }

  fun createAccount(onAccountCreated: (String, String) -> Unit) {
    viewModelScope.launch {
      try {
        _registerState.value = _registerState.value.copy(loading = true)
        val username = _registerState.value.userName
        val password = _registerState.value.password
        registerInteractor.registerWithParams(username, password)
        val response = loginInteractor.authorize(username, password)
        response?.let { AuthStorage.saveAuthData(it) }
        loginInteractor.saveData(username, password)
        withContext(appDispatchers.main()) {
          onAccountCreated.invoke(username, password)
        }
      } catch (t: Throwable) {
        _registerState.value = _registerState.value.copy(loading = false, error = t)
        Timber.e(t, "Error occurred while creating account")
      }
    }
  }
}

data class RegisterState(
  val userName: String = "",
  val password: String = "",
  val requestUserName: Boolean,
  val loading: Boolean = false,
  val error: Throwable? = null
)