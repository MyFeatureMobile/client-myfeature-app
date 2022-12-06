package com.myfeature.mobile.domain.repository

import com.myfeature.mobile.data.model.AuthParams
import com.myfeature.mobile.data.model.AuthResponse
import com.myfeature.mobile.data.model.UserResponse

interface LoginRepository {

  suspend fun authorize(data: AuthParams): UserResponse?
}