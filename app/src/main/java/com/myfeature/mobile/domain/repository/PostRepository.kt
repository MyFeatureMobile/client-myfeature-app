package com.myfeature.mobile.domain.repository

import com.myfeature.mobile.ui.home.post.create.PostCreateState

interface PostRepository {

  suspend fun createPost(post: PostCreateState)
}