package com.myfeature.mobile.data

import com.myfeature.mobile.core.utils.DEFAULT_AVATAR
import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.data.mapper.toFeaturePost
import com.myfeature.mobile.data.model.feature.FeatureResponse
import com.myfeature.mobile.data.model.feature.User
import com.myfeature.mobile.domain.repository.PostRepository
import com.myfeature.mobile.ui.home.post.create.PostCreateState
import com.myfeature.mobile.ui.home.post.model.PostModel
import com.myfeature.mobile.ui.home.post.model.UserOnPost
import com.myfeature.mobile.ui.home.profile.model.PostItem
import org.koin.java.KoinJavaComponent.get

class PostRepositoryImpl(private val myFeatureApi: MyFeatureApi = get(MyFeatureApi::class.java)) : PostRepository {

  override suspend fun createPost(post: PostCreateState) {
    myFeatureApi.createPost(post.toFeaturePost())
  }

  override suspend fun getPosts(): List<PostItem> {
    return myFeatureApi.getPosts().map { it.toPostItem() }
  }

  override suspend fun getPostById(postId: Long): PostModel? {
    return try {
      myFeatureApi.getPost(postId).toPostModel()
    } catch (e: Exception) {
      null
    }
  }
}

private fun FeatureResponse.toPostModel(): PostModel {
  return PostModel(
    id = featureId ?: 0,
    photoUrls = photo?.url?.let { listOf(it) } ?: emptyList(),
    likesCount = 52,
    liked = false,
    userOnPost = user.toUserOnPost(),
    description = this.content ?: "",
    codeLink = this.name ?: ""
  )
}

private fun FeatureResponse.toPostItem(): PostItem {
  return PostItem(
    id = featureId ?: 0,
    photoUrls = photo?.url?.let { listOf(it) } ?: emptyList(),
    likeCount = 52,
  )
}

private fun User?.toUserOnPost(): UserOnPost {
  if (this == null) {
    return UserOnPost(0L, "default", DEFAULT_AVATAR)
  }
  return UserOnPost(
    userId = userId ?: 0L,
    nickname = username ?: "default",
    userPhotoUrl = avatar?.url ?: DEFAULT_AVATAR
  )
}
