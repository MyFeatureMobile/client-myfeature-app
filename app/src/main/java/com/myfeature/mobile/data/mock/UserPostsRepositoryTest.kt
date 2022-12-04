package com.myfeature.mobile.data.mock

import com.myfeature.mobile.domain.repository.UserPostsRepository
import com.myfeature.mobile.ui.home.profile.model.PostItem

class UserPostsRepositoryTest : UserPostsRepository {

  override suspend fun getUsersPosts(userId: String): List<PostItem> {
    return TestData.posts
  }

  override suspend fun getFollowingPostsForUser(): List<PostItem> {
    return TestData.posts
  }

  override suspend fun getRecommendationsForUser(): List<PostItem> {
    return TestData.posts
  }
}