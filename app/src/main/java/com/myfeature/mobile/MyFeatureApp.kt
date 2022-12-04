package com.myfeature.mobile

import android.app.Application
import com.myfeature.mobile.di.AppModule.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant

class MyFeatureApp : Application() {

  override fun onCreate() {
    super.onCreate()
    if (BuildConfig.DEBUG) {
      plant(DebugTree())
    }

    initDiWithKoin()
  }

  private fun initDiWithKoin() {
    startKoin {
      androidLogger()
      androidContext(this@MyFeatureApp)
      modules(appModule)
    }
  }
}