package com.myfeature.mobile.domain.repository

import com.myfeature.mobile.domain.model.LoginData
import com.myfeature.mobile.domain.model.UserProfile

interface RegisterRepository {

  suspend fun register(loginData: LoginData): UserProfile
}