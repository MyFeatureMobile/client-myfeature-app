package com.myfeature.mobile.data

import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.domain.model.UserProfile
import com.myfeature.mobile.domain.repository.ProfileRepository
import org.koin.java.KoinJavaComponent.get

class ProfileRepositoryImpl(
  private val myFeatureApi: MyFeatureApi = get(MyFeatureApi::class.java)
) : ProfileRepository {

  override suspend fun getProfile(userId: String): UserProfile {
    return myFeatureApi.getProfile(userId)
  }

  override suspend fun updateProfile(userName: String, email: String, description: String) {
    TODO("Not yet implemented")
  }
}