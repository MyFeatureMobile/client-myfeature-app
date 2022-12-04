package com.myfeature.mobile.domain.repository

import com.myfeature.mobile.domain.model.LoginData

interface RegisterRepository {

  suspend fun register(loginData: LoginData)
}