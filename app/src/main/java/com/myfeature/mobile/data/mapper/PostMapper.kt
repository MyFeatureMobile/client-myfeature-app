package com.myfeature.mobile.data.mapper

import com.myfeature.mobile.data.model.CreatePostParams
import com.myfeature.mobile.ui.home.post.create.PostCreateState

fun PostCreateState.toFeaturePost(): CreatePostParams {
  return CreatePostParams(photoUrl, description, githubLink, code)
}