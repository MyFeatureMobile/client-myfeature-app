package com.myfeature.mobile.di

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.myfeature.mobile.core.coroutines.AppDispatchers
import com.myfeature.mobile.data.LoginDataLocalStorage
import com.myfeature.mobile.data.LoginRepository
import com.myfeature.mobile.domain.LoginInteractor

@SuppressLint("StaticFieldLeak")
object GraphDI {

  lateinit var context: Context

  val gson by lazy { Gson() }

  // Login
  val loginDataLocalStorage: LoginDataLocalStorage by lazy {
    LoginDataLocalStorage(context, appDispatchers, gson)
  }

  val loginRepository: LoginRepository by lazy {
    LoginRepository()
  }

  val loginInteractor: LoginInteractor by lazy {
    LoginInteractor()
  }

  // Coroutines
  val appDispatchers: AppDispatchers = AppDispatchers()

  fun provideContext(context: Context) {
    this.context = context
  }
}