package com.myfeature.mobile.domain

import com.myfeature.mobile.data.TestData
import com.myfeature.mobile.ui.home.profile.model.PostItem

interface UserPostsInteractor {

  suspend fun getUserPosts(userId: String): List<PostItem>
}

class UserPostsInteractorTest() : UserPostsInteractor {

  // TODO: Create repository
  override suspend fun getUserPosts(userId: String): List<PostItem> {
    return TestData.posts
  }
}