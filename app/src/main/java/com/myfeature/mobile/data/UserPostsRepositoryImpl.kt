package com.myfeature.mobile.data

import com.myfeature.mobile.data.api.MyFeatureApi
import com.myfeature.mobile.domain.repository.UserPostsRepository
import com.myfeature.mobile.ui.home.profile.model.PostItem
import org.koin.java.KoinJavaComponent

class UserPostsRepositoryImpl(
  private val myFeatureApi: MyFeatureApi = KoinJavaComponent.get(MyFeatureApi::class.java)
) : UserPostsRepository {

  override suspend fun getUsersPosts(userId: String): List<PostItem> {
    TODO("Not yet implemented")
  }

  override suspend fun getFollowingPostsForUser(): List<PostItem> {
    TODO("Not yet implemented")
  }

  override suspend fun getRecommendationsForUser(): List<PostItem> {
    TODO("Not yet implemented")
  }
}