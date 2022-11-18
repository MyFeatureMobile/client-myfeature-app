package com.myfeature.mobile.data

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import com.myfeature.mobile.core.coroutines.AppDispatchers
import com.myfeature.mobile.data.model.StoredLoginData
import kotlinx.coroutines.withContext

class LoginDataLocalStorage(
  context: Context,
  private val appDispatchers: AppDispatchers,
  private val gson: Gson
) {

  private val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

  private val sharedPreferences = EncryptedSharedPreferences.create(
    LOGIN_SHARED_STORAGE_NAME,
    masterKeyAlias,
    context,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
  )

  suspend fun getDataFromStorage(): StoredLoginData? {
    return withContext(appDispatchers.io()) {
      val data = sharedPreferences.getString(LOGIN_DATA, "") ?: ""
      gson.fromJson(data, StoredLoginData::class.java)
    }
  }

  suspend fun saveData(storedLoginData: StoredLoginData) {
    withContext(appDispatchers.io()) {
      val jsonString = gson.toJson(storedLoginData)
      sharedPreferences.edit().putString(LOGIN_DATA, jsonString).apply()
    }
  }

  companion object {

    private const val LOGIN_SHARED_STORAGE_NAME = "LoginSharedStorageName"

    private const val LOGIN_DATA = "login_data"
  }
}