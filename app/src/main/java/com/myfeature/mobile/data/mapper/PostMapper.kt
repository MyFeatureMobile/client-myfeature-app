package com.myfeature.mobile.data.mapper

import com.myfeature.mobile.data.AuthStorage
import com.myfeature.mobile.data.model.CreatePostParams
import com.myfeature.mobile.data.model.feature.LoadedPhoto
import com.myfeature.mobile.ui.home.post.create.PostCreateState

fun PostCreateState.toFeaturePost(loadedPhoto: LoadedPhoto): CreatePostParams {
  return CreatePostParams(loadedPhoto.id, description, githubLink, AuthStorage.userId)
}