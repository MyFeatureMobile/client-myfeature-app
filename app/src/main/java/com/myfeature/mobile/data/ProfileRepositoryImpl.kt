package com.myfeature.mobile.data

import com.myfeature.mobile.core.utils.DEFAULT_AVATAR
import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.data.mapper.toUserProfile
import com.myfeature.mobile.domain.model.UserProfile
import com.myfeature.mobile.domain.repository.ProfileRepository
import org.koin.java.KoinJavaComponent.get

class ProfileRepositoryImpl(
  private val myFeatureApi: MyFeatureApi = get(MyFeatureApi::class.java)
) : ProfileRepository {

  override suspend fun getProfile(userId: Long): UserProfile {
    val res = myFeatureApi.getProfile(userId)
    return res.toUserProfile(res.avatar?.url ?: DEFAULT_AVATAR)
  }

  override suspend fun updateProfile(userName: String, email: String, description: String) {
//    myFeatureApi.updateUserName()
  }
}