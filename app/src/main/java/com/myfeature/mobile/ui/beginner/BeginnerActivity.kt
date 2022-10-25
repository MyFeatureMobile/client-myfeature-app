package com.myfeature.mobile.ui.beginner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.myfeature.mobile.domain.LoginData
import com.myfeature.mobile.ui.navigation.BeginApp

class BeginnerActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BeginApp(::onLogin)
    }
  }

  private fun onLogin(data: LoginData) {

  }
}