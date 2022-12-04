package com.myfeature.mobile.data

import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.data.mapper.toFeaturePost
import com.myfeature.mobile.domain.repository.PostRepository
import com.myfeature.mobile.ui.home.post.create.PostCreateState
import org.koin.java.KoinJavaComponent.get

class PostRepositoryImpl(private val myFeatureApi: MyFeatureApi = get(MyFeatureApi::class.java)) : PostRepository {

  override suspend fun createPost(post: PostCreateState) {
    myFeatureApi.createPost(post.toFeaturePost())
  }
}