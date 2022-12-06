package com.myfeature.mobile.ui.home.timeline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfeature.mobile.domain.repository.PostRepository
import com.myfeature.mobile.ui.home.profile.model.PostItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class TimelineViewModel(private val postRepository: PostRepository = get(PostRepository::class.java)) : ViewModel() {

  private val _timeline = MutableStateFlow<List<PostItem>>(emptyList())

  val timeline: StateFlow<List<PostItem>>
    get() = _timeline

  init {
    loadTimeline()
  }

  fun loadTimeline() {
    viewModelScope.launch {
      _timeline.value = postRepository.getPosts()
    }
  }
}