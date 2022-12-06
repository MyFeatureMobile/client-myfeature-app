package com.myfeature.mobile.domain.repository

import com.myfeature.mobile.ui.home.profile.model.PostItem

interface UserPostsRepository {

  suspend fun getUsersPosts(userId: Long): List<PostItem>

  suspend fun getFollowingPostsForUser(): List<PostItem>

  suspend fun getRecommendationsForUser(): List<PostItem>
}