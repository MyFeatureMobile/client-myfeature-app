package com.myfeature.mobile.data

import com.myfeature.mobile.data.model.FeaturePost
import com.myfeature.mobile.di.GraphDI
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface PostRepository {

  suspend fun createPost(post: FeaturePost)
}

class PostRepositoryTest : PostRepository {

  private val appDispatchers = GraphDI.appDispatchers

  override suspend fun createPost(post: FeaturePost) {
    withContext(appDispatchers.io()) {
      delay(500)
    }
  }
}