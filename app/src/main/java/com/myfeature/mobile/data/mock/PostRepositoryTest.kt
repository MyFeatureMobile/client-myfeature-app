package com.myfeature.mobile.data.mock

import com.myfeature.mobile.core.coroutines.AppDispatchers
import com.myfeature.mobile.domain.repository.PostRepository
import com.myfeature.mobile.ui.home.post.create.PostCreateState
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.koin.java.KoinJavaComponent.inject

class PostRepositoryTest : PostRepository {

  private val appDispatchers: AppDispatchers by inject(AppDispatchers::class.java)

  override suspend fun createPost(post: PostCreateState) {
    withContext(appDispatchers.io()) {
      delay(500)
    }
  }
}