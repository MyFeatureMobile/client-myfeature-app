package com.myfeature.mobile

import android.app.Application
import com.myfeature.mobile.di.GraphDI
import timber.log.Timber.DebugTree
import timber.log.Timber.Forest.plant


class MyFeatureApp : Application() {

  override fun onCreate() {
    super.onCreate()
    GraphDI.context = applicationContext
    if (BuildConfig.DEBUG) {
      plant(DebugTree())
    }
  }
}