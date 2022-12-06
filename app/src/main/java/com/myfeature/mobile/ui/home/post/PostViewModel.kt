package com.myfeature.mobile.ui.home.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfeature.mobile.domain.repository.PostRepository
import com.myfeature.mobile.ui.home.post.PostState.Loading
import com.myfeature.mobile.ui.home.post.PostState.Post
import com.myfeature.mobile.ui.home.post.model.PostModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class PostViewModel(
  private val postRepository: PostRepository = get(PostRepository::class.java)
) : ViewModel() {

  private val postModelState = MutableStateFlow<PostState>(Loading)

  fun listenToPostModel(postId: Long): Flow<PostState> {
    viewModelScope.launch {
      val post = postRepository.getPostById(postId)
      if (post != null) {
        postModelState.value = Post(post)
      } else {
        postModelState.value = PostState.Error
      }
    }
    return postModelState
  }
}

sealed class PostState {
  object Loading : PostState()

  class Post(val post: PostModel) : PostState()

  object Error : PostState()
}