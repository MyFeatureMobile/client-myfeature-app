package com.myfeature.mobile.data

import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.data.model.CreateUserParams
import com.myfeature.mobile.data.model.UserResponse
import com.myfeature.mobile.domain.model.LoginData
import com.myfeature.mobile.domain.model.UserProfile
import com.myfeature.mobile.domain.repository.RegisterRepository
import org.koin.java.KoinJavaComponent.get

class RegisterRepositoryImpl(
  private val myFeatureApi: MyFeatureApi = get(MyFeatureApi::class.java)
) : RegisterRepository {

  override suspend fun register(loginData: LoginData): UserProfile {
    val res = myFeatureApi.createUser(loginData.toCreateUserParams())
    return res.toUserProfile()
//    return if (res) {
//      res.body()?.toUserProfile() ?: throw NullPointerException("No user got on register.")
//    } else {
//      throw RuntimeException(res.message())
//    }
  }

  private fun LoginData.toCreateUserParams(): CreateUserParams {
    return CreateUserParams(userName, password)
  }

  private fun UserResponse.toUserProfile(): UserProfile {
    return UserProfile(
      userId = userId,
      userName = username ?: "",
      avatarUrl = null, // TODO
      description = description,
      email = email,
      postsCount = postCount,
      followersCount = 0,
      followingCount = 0
    )
  }
}