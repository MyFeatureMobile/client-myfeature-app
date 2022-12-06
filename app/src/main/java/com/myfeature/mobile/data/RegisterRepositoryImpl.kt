package com.myfeature.mobile.data

import com.myfeature.mobile.core.utils.DEFAULT_AVATAR
import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.data.mapper.toUserProfile
import com.myfeature.mobile.data.model.AvatarParams
import com.myfeature.mobile.data.model.CreateUserParams
import com.myfeature.mobile.data.model.UserResponse
import com.myfeature.mobile.domain.model.LoginData
import com.myfeature.mobile.domain.model.UserProfile
import com.myfeature.mobile.domain.repository.RegisterRepository
import org.koin.java.KoinJavaComponent.get

class RegisterRepositoryImpl(
  private val myFeatureApi: MyFeatureApi = get(MyFeatureApi::class.java),
  private val photoRepository: PhotoRepository = get(PhotoRepository::class.java)
) : RegisterRepository {

  override suspend fun register(loginData: LoginData): UserProfile {
    val res = myFeatureApi.createUser(loginData.toCreateUserParams())
    val loadedPhoto = photoRepository.addPhoto(DEFAULT_AVATAR)
    myFeatureApi.updateUserAvatar(AuthStorage.userId, AvatarParams(loadedPhoto.id))
    return res.toUserProfile(DEFAULT_AVATAR)
  }

  private fun LoginData.toCreateUserParams(): CreateUserParams {
    return CreateUserParams(userName, password)
  }
}