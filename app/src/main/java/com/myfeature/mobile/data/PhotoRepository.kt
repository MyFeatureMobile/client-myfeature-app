package com.myfeature.mobile.data

import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.data.model.feature.LoadedPhoto
import com.myfeature.mobile.data.model.feature.PhotoUrl
import org.koin.java.KoinJavaComponent

class PhotoRepository(
  private val myFeatureApi: MyFeatureApi = KoinJavaComponent.get(MyFeatureApi::class.java)
) {

  suspend fun addPhoto(url: String): LoadedPhoto {
    return myFeatureApi.loadPhoto(PhotoUrl(url))
  }

  suspend fun getPhotoById(id: Long): PhotoUrl {
    return myFeatureApi.getPhotoById(id)
  }
}