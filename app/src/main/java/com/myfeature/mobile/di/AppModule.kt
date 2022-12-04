package com.myfeature.mobile.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import com.myfeature.mobile.core.coroutines.AppDispatchers
import com.myfeature.mobile.core.utils.API_URL
import com.myfeature.mobile.core.utils.REAL_DATA_USED
import com.myfeature.mobile.data.LoginDataLocalStorage
import com.myfeature.mobile.data.LoginRepositoryImpl
import com.myfeature.mobile.data.PostRepositoryImpl
import com.myfeature.mobile.data.ProfileRepositoryImpl
import com.myfeature.mobile.data.RegisterRepositoryImpl
import com.myfeature.mobile.data.UserPostsRepositoryImpl
import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.data.mock.LoginRepositoryTest
import com.myfeature.mobile.data.mock.PostRepositoryTest
import com.myfeature.mobile.data.mock.ProfileRepositoryTest
import com.myfeature.mobile.data.mock.RegisterRepositoryTest
import com.myfeature.mobile.data.mock.UserPostsRepositoryTest
import com.myfeature.mobile.domain.LoginInteractor
import com.myfeature.mobile.domain.RegisterInteractor
import com.myfeature.mobile.domain.repository.LoginRepository
import com.myfeature.mobile.domain.repository.PostRepository
import com.myfeature.mobile.domain.repository.ProfileRepository
import com.myfeature.mobile.domain.repository.RegisterRepository
import com.myfeature.mobile.domain.repository.UserPostsRepository
import com.myfeature.mobile.ui.beginner.loading.LoadingViewModel
import com.myfeature.mobile.ui.beginner.login.LoginViewModel
import com.myfeature.mobile.ui.beginner.register.RegisterViewModel
import com.myfeature.mobile.ui.home.post.create.PostCreateViewModel
import com.myfeature.mobile.ui.home.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModule {

  val appModule = module {
    baseDependencies()
    dataSources()

    single { LoginInteractor(get(), get()) }
    single { RegisterInteractor(get()) }

    viewModelOf(::LoadingViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::PostCreateViewModel)
    viewModelOf(::ProfileViewModel)
  }

  private fun Module.baseDependencies() {
    single { createGson() }
    single { AppDispatchers() }
    single { LoginDataLocalStorage(get(), get(), get()) }
  }

  private fun Module.dataSources() {
    if (REAL_DATA_USED) {
      factory { createRetrofit(get()) }
      factory { createMyFeatureApi(get()) }
      realDataSources()
    } else {
      mockDataSource()
    }
  }

  private fun Module.mockDataSource() {
    factoryOf<LoginRepository>(::LoginRepositoryTest)
    factoryOf<ProfileRepository>(::ProfileRepositoryTest)
    single<PostRepository> { PostRepositoryTest() }
    single<UserPostsRepository> { UserPostsRepositoryTest() }
    single<RegisterRepository> { RegisterRepositoryTest() }
  }

  private fun Module.realDataSources() {
    factoryOf<LoginRepository>(::LoginRepositoryImpl)
    factoryOf<ProfileRepository>(::ProfileRepositoryImpl)
    factoryOf<PostRepository>(::PostRepositoryImpl)
    factoryOf<UserPostsRepository>(::UserPostsRepositoryImpl)
    factoryOf<RegisterRepository>(::RegisterRepositoryImpl)
  }

  private fun createMyFeatureApi(retrofit: Retrofit): MyFeatureApi {
    return retrofit.create(MyFeatureApi::class.java)
  }

  internal fun createRetrofit(gson: Gson): Retrofit {
    return Retrofit.Builder()
      .baseUrl(API_URL)
      .addCallAdapterFactory(CoroutineCallAdapterFactory())
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build()
  }

  internal fun createGson(): Gson {
    return GsonBuilder().create()
  }
}