package com.myfeature.mobile.domain.repository

import com.myfeature.mobile.data.model.UserProfile

interface ProfileRepository {

  suspend fun getProfile(userId: String): UserProfile

  suspend fun updateProfile(userName: String, email: String, description: String)
}