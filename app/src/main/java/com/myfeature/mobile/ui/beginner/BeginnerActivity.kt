package com.myfeature.mobile.ui.beginner

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.myfeature.mobile.domain.LoginData
import com.myfeature.mobile.ui.beginner.navigation.BeginApp
import com.myfeature.mobile.ui.home.HomeActivity

class BeginnerActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      BeginApp(::onLoggedAlready, ::onLogin)
    }
  }

  private fun onLoggedAlready() {
    startActivity(Intent(this, HomeActivity::class.java))
    this.finish()
  }

  private fun onLogin(data: LoginData) {
    startActivity(Intent(this, HomeActivity::class.java))
    this.finish()
  }
}