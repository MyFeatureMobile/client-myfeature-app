package com.myfeature.mobile.data

import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.domain.repository.PostRepository
import com.myfeature.mobile.domain.repository.UserPostsRepository
import com.myfeature.mobile.ui.home.profile.model.PostItem
import org.koin.java.KoinJavaComponent
import org.koin.java.KoinJavaComponent.get

class UserPostsRepositoryImpl(
  private val myFeatureApi: MyFeatureApi = KoinJavaComponent.get(MyFeatureApi::class.java),
  private val postsRepository: PostRepository = get(PostRepository::class.java)
) : UserPostsRepository {

  override suspend fun getUsersPosts(userId: Long): List<PostItem> {
    return myFeatureApi.getPosts().filter { it.user?.userId == userId }.map { it.toPostItem() }.asReversed()
  }

  override suspend fun getFollowingPostsForUser(): List<PostItem> {
    return emptyList()
  }

  override suspend fun getRecommendationsForUser(): List<PostItem> {
    return emptyList()
  }
}