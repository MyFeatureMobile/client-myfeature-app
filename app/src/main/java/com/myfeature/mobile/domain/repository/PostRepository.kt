package com.myfeature.mobile.domain.repository

import com.myfeature.mobile.data.model.FeaturePost

interface PostRepository {

  suspend fun createPost(post: FeaturePost)
}