package com.myfeature.mobile.ui.home.post.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfeature.mobile.data.mapper.toFeaturePost
import com.myfeature.mobile.domain.repository.PostRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PostCreateViewModel(
  private val postRepository: PostRepository
) : ViewModel() {

  val inputState: StateFlow<PostCreateState>
    get() = _inputState

  private val _inputState = MutableStateFlow(PostCreateState.EMPTY)

  fun updatePhotoUrl(photoUrl: String) {
    val current = _inputState.value
    val newPhotoList = current.photoUrl + photoUrl
    _inputState.value = current.copy(photoUrl = newPhotoList)
  }

  fun changeDescription(description: String) {
    _inputState.value = _inputState.value.copy(description = description)
  }

  fun changeGitHubLink(link: String) {
    _inputState.value = _inputState.value.copy(githubLink = link)
  }

  fun updateCode(code: String) {
    _inputState.value = _inputState.value.copy(code = code)
  }

  fun createPost(onCompleted: () -> Unit) {
    viewModelScope.launch {
      postRepository.createPost(_inputState.value.toFeaturePost())
      onCompleted.invoke()
    }
  }
}

data class PostCreateState(
  val photoUrl: List<String> = emptyList(),
  val description: String = "",
  val githubLink: String = "",
  val code: String = ""
) {

  companion object {

    val EMPTY = PostCreateState()
  }
}