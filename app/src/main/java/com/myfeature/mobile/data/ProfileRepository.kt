package com.myfeature.mobile.data

import com.myfeature.mobile.data.TestData.userProfile
import com.myfeature.mobile.data.model.UserProfile
import com.myfeature.mobile.di.GraphDI
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface ProfileRepository {

  suspend fun getProfile(userId: String): UserProfile
}

class ProfileRepositoryTest : ProfileRepository {

  private val appDispatchers = GraphDI.appDispatchers

  override suspend fun getProfile(userId: String): UserProfile {
    return withContext(appDispatchers.io()) {
      delay(500)
      userProfile
    }
  }
}