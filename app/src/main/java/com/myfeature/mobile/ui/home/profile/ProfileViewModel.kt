package com.myfeature.mobile.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfeature.mobile.data.AuthStorage
import com.myfeature.mobile.data.ProfileRepository
import com.myfeature.mobile.data.model.UserProfile
import com.myfeature.mobile.di.GraphDI
import com.myfeature.mobile.domain.LoginInteractor
import com.myfeature.mobile.domain.UserPostsInteractor
import com.myfeature.mobile.ui.home.profile.ProfileContent.FollowersContent
import com.myfeature.mobile.ui.home.profile.ProfileContent.FollowingContent
import com.myfeature.mobile.ui.home.profile.ProfileContent.PostsContent
import com.myfeature.mobile.ui.home.profile.model.Category
import com.myfeature.mobile.ui.home.profile.model.Category.FOLLOWERS
import com.myfeature.mobile.ui.home.profile.model.Category.FOLLOWING
import com.myfeature.mobile.ui.home.profile.model.Category.POSTS
import com.myfeature.mobile.ui.home.profile.model.PostItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {

  val profileState: Flow<ProfileState?>
    get() = _state.asStateFlow()

  private val _state: MutableStateFlow<ProfileState?> = MutableStateFlow(null)

  val refreshing: StateFlow<Boolean>
    get() = _refreshing

  private val _refreshing = MutableStateFlow(true)

  private val selectedCategory = MutableStateFlow(DEFAULT_PRESELECTED_CATEGORY)

  val contentState: Flow<ProfileContent>
    get() = _contentState.filterNotNull()

  private val _contentState: MutableStateFlow<ProfileContent?> = MutableStateFlow(null)

  private val loginInteractor: LoginInteractor = GraphDI.loginInteractor
  private val profileRepository: ProfileRepository = GraphDI.profileRepository
  private val userPostsInteractor: UserPostsInteractor = GraphDI.userPostsInteractor

  init {
    viewModelScope.launch {
      selectedCategory
        .onEach { _refreshing.value = true }
        .collectLatest {
          loadDataForCategory(selectedCategory.value)
          _refreshing.value = false
        }
    }
  }

  fun loadData() {
    loadProfile()
  }

  private fun loadProfile() {
    _refreshing.value = true
    viewModelScope.launch {
      val profile = profileRepository.getProfile(AuthStorage.userId)
      _state.value = _state.value?.profileToState(profile) ?: ProfileState.EMPTY().profileToState(profile)
      _refreshing.value = false
    }
  }

  private suspend fun loadDataForCategory(chosenCategory: Category) {
    when (chosenCategory) {
      POSTS -> {
        val posts = userPostsInteractor.getUserPosts(AuthStorage.userId)
        _contentState.value = PostsContent(posts)
      }
      FOLLOWERS -> {
        _contentState.value = FollowersContent(emptyList())
      }
      FOLLOWING -> {
        _contentState.value = FollowingContent(emptyList())
      }
    }
  }

  fun logOut() {
    viewModelScope.launch {
      loginInteractor.logOut()
    }
  }

  fun selectCategory(category: Category) {
    selectedCategory.value = category
  }
}

data class ProfileState(
  val userName: String,
  val avatarUrl: String,
  val postsCount: Int,
  val followersCount: Int,
  val followingCount: Int,
  val category: Category
) {

  fun profileToState(profile: UserProfile): ProfileState {
    return ProfileState(
      userName = profile.userName,
      avatarUrl = profile.avatarUrl,
      postsCount = profile.postsCount.toInt(),
      followingCount = profile.followingCount.toInt(),
      followersCount = profile.followersCount.toInt(),
      category = category
    )
  }

  companion object {
    fun EMPTY() = ProfileState(
      "", "", 0, 0, 0, POSTS
    )
  }
}

sealed class ProfileContent(
  val category: Category
) {
  class Empty : ProfileContent(DEFAULT_PRESELECTED_CATEGORY)
  class PostsContent(val posts: List<PostItem>) : ProfileContent(POSTS)
  class FollowersContent(val followers: List<Any>) : ProfileContent(FOLLOWERS)
  class FollowingContent(val following: List<Any>) : ProfileContent(FOLLOWING)
}