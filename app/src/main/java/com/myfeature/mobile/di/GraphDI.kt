package com.myfeature.mobile.di

import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.myfeature.mobile.core.coroutines.AppDispatchers
import com.myfeature.mobile.data.LoginDataLocalStorage
import com.myfeature.mobile.data.LoginRepository
import com.myfeature.mobile.data.PostRepository
import com.myfeature.mobile.data.PostRepositoryTest
import com.myfeature.mobile.data.ProfileRepository
import com.myfeature.mobile.data.ProfileRepositoryTest
import com.myfeature.mobile.data.RegisterRepository
import com.myfeature.mobile.domain.LoginInteractor
import com.myfeature.mobile.domain.RegisterInteractor
import com.myfeature.mobile.domain.UserPostsInteractor
import com.myfeature.mobile.domain.UserPostsInteractorTest

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

  val registerRepository: RegisterRepository by lazy {
    RegisterRepository()
  }

  val registerInteractor: RegisterInteractor by lazy {
    RegisterInteractor()
  }

  // Repo with interface
  val profileRepository: ProfileRepository by lazy {
    ProfileRepositoryTest()
  }

  val userPostsInteractor: UserPostsInteractor by lazy {
    UserPostsInteractorTest()
  }

  val postRepository: PostRepository by lazy {
    PostRepositoryTest()
  }

  // Coroutines
  val appDispatchers: AppDispatchers = AppDispatchers()
}