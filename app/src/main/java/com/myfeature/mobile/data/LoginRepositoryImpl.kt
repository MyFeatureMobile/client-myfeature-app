package com.myfeature.mobile.data

import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.data.model.AuthParams
import com.myfeature.mobile.data.model.UserResponse
import com.myfeature.mobile.domain.repository.LoginRepository
import org.koin.java.KoinJavaComponent.get

class LoginRepositoryImpl(
  private val myFeatureApi: MyFeatureApi = get(MyFeatureApi::class.java)
): LoginRepository {

  override suspend fun authorize(data: AuthParams): UserResponse {
    return myFeatureApi.authorize(data)
  }
}