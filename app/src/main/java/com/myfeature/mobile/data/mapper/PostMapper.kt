package com.myfeature.mobile.data.mapper

import com.myfeature.mobile.data.model.FeaturePost
import com.myfeature.mobile.ui.home.post.create.PostCreateState

fun PostCreateState.toFeaturePost(): FeaturePost {
  return FeaturePost(photoUrl, description, githubLink, code)
}