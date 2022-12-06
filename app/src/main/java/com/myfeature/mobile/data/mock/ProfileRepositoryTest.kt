package com.myfeature.mobile.data.mock

import com.myfeature.mobile.core.coroutines.AppDispatchers
import com.myfeature.mobile.data.mock.TestData.userProfile
import com.myfeature.mobile.domain.model.UserProfile
import com.myfeature.mobile.domain.repository.ProfileRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class ProfileRepositoryTest : ProfileRepository {

  private val appDispatchers: AppDispatchers by inject(AppDispatchers::class.java)

  override suspend fun getProfile(userId: Long): UserProfile {
    return withContext(appDispatchers.io()) {
      delay(500)
      userProfile
    }
  }

  override suspend fun updateProfile(userName: String, email: String, description: String) {
    return withContext(appDispatchers.io()) {
      delay(500)
    }
  }
}