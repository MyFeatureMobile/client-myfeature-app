package com.myfeature.mobile.data.mapper

import com.myfeature.mobile.data.model.AuthParams
import com.myfeature.mobile.data.model.StoredLoginData

fun AuthParams.toStoredLoginData(): StoredLoginData {
  return StoredLoginData(username, password)
}

fun StoredLoginData.toLoginParams(): AuthParams {
  return AuthParams(username, password)
}