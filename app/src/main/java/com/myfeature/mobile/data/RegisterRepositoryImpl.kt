package com.myfeature.mobile.data

import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.domain.model.LoginData
import com.myfeature.mobile.domain.repository.RegisterRepository
import org.koin.java.KoinJavaComponent.get

class RegisterRepositoryImpl(
  private val myFeatureApi: MyFeatureApi = get(MyFeatureApi::class.java)
) : RegisterRepository {

  override suspend fun register(loginData: LoginData) {
    TODO("Not yet implemented")
  }
}