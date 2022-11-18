package com.myfeature.mobile.ui.beginner.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfeature.mobile.data.AuthStorage
import com.myfeature.mobile.data.model.StoredLoginData
import com.myfeature.mobile.di.GraphDI
import com.myfeature.mobile.domain.LoginInteractor
import com.myfeature.mobile.ui.beginner.loading.AuthorizationNeed.Authorized
import com.myfeature.mobile.ui.beginner.loading.AuthorizationNeed.Need
import com.myfeature.mobile.ui.beginner.loading.AuthorizationNeed.NoNeedAuthorizing
import com.myfeature.mobile.ui.beginner.loading.AuthorizationNeed.NotRequested
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoadingViewModel : ViewModel() {

  val needToAuthorizeState: StateFlow<AuthorizationNeed>
    get() = _needToAuthorizeState

  private val _needToAuthorizeState = MutableStateFlow<AuthorizationNeed>(NotRequested)

  private val loginInteractor: LoginInteractor by lazy { GraphDI.loginInteractor }

  fun requestAuthNeed() {
    viewModelScope.launch {
      val data = loginInteractor.savedAuthData()
      if (data == null) {
        _needToAuthorizeState.value = Need
        return@launch
      }
      _needToAuthorizeState.value = NoNeedAuthorizing(data)
    }
  }

  fun authorize(data: StoredLoginData) {
    viewModelScope.launch {
      try {
        val response = loginInteractor.authorize(data.username, data.password)
        if (response == null) {
          _needToAuthorizeState.value = Need
        } else {
          AuthStorage.saveAuthData(response)
          _needToAuthorizeState.value = Authorized
        }
      } catch (e: Exception) {
        _needToAuthorizeState.value = Need
      }
    }
  }
}

sealed class AuthorizationNeed {

  object NotRequested : AuthorizationNeed()

  class NoNeedAuthorizing(val storedLoginData: StoredLoginData) : AuthorizationNeed()

  object Authorized : AuthorizationNeed()

  object Need : AuthorizationNeed()
}