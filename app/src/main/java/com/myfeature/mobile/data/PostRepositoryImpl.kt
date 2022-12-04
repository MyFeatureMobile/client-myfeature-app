package com.myfeature.mobile.data

import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.data.model.FeaturePost
import com.myfeature.mobile.domain.repository.PostRepository
import org.koin.java.KoinJavaComponent.get

class PostRepositoryImpl(private val myFeatureApi: MyFeatureApi = get(MyFeatureApi::class.java)) : PostRepository {

  override suspend fun createPost(post: FeaturePost) {
    TODO("Not yet implemented")
  }
}