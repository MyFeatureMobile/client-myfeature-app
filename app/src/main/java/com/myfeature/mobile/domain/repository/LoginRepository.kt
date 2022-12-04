package com.myfeature.mobile.domain.repository

import com.myfeature.mobile.data.model.AuthParams
import com.myfeature.mobile.data.model.AuthResponse

interface LoginRepository {

  suspend fun authorize(data: AuthParams): AuthResponse?
}