package com.myfeature.mobile.domain.repository

import com.myfeature.mobile.ui.home.post.create.PostCreateState
import com.myfeature.mobile.ui.home.post.model.PostModel
import com.myfeature.mobile.ui.home.profile.model.PostItem

interface PostRepository {

  suspend fun createPost(post: PostCreateState)

  suspend fun getPostById(postId: Long): PostModel?
  suspend fun getPosts(): List<PostItem>
}