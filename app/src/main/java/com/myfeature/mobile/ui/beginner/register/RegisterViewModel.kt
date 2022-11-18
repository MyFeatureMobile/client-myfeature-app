package com.myfeature.mobile.ui.beginner.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfeature.mobile.di.GraphDI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class RegisterViewModel : ViewModel() {

  val registerState: Flow<RegisterState>
    get() = _registerState

  private val _registerState = MutableStateFlow(RegisterState("", "", requestUserName = true, loading = false))

  private val appDispatchers = GraphDI.appDispatchers
  private val loginInteractor = GraphDI.loginInteractor
  private val registerInteractor = GraphDI.registerInteractor

  private val coroutineExceptionHandler = CoroutineExceptionHandler { _, t ->
    _registerState.value = _registerState.value.copy(error = t)
    Timber.e(t, "Error occurred while creating account")
  }

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
        registerInteractor.registerAndSave(username, password)
        loginInteractor.authorize(username, password)
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