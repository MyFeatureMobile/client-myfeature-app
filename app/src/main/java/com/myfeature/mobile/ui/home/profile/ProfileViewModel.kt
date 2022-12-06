package com.myfeature.mobile.ui.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myfeature.mobile.data.AuthStorage
import com.myfeature.mobile.data.mock.TestData.avatarDefault
import com.myfeature.mobile.domain.LoginInteractor
import com.myfeature.mobile.domain.model.UserProfile
import com.myfeature.mobile.domain.repository.ProfileRepository
import com.myfeature.mobile.domain.repository.UserPostsRepository
import com.myfeature.mobile.ui.home.profile.ProfileContent.FollowersContent
import com.myfeature.mobile.ui.home.profile.ProfileContent.FollowingContent
import com.myfeature.mobile.ui.home.profile.ProfileContent.PostsContent
import com.myfeature.mobile.ui.home.profile.model.Category
import com.myfeature.mobile.ui.home.profile.model.Category.FOLLOWERS
import com.myfeature.mobile.ui.home.profile.model.Category.FOLLOWING
import com.myfeature.mobile.ui.home.profile.model.Category.POSTS
import com.myfeature.mobile.ui.home.profile.model.PostItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.get

class ProfileViewModel(
  private val loginInteractor: LoginInteractor = get(LoginInteractor::class.java),
  private val profileRepository: ProfileRepository = get(ProfileRepository::class.java),
  private val userPostsRepository: UserPostsRepository = get(UserPostsRepository::class.java)
) : ViewModel() {

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

  private var loadProfileJob: Job? = null
  private var waitingFirstDataJob: Job? = null

  init {
    viewModelScope.launch {
      selectedCategory
        .onEach { _refreshing.value = true }
        .collectLatest {
          loadDataForCategory(selectedCategory.value)
          _refreshing.value = false
        }
    }
    loadData()
  }

  fun loadData() {
    loadProfileJob?.cancel()
    loadProfileJob = null
    loadProfileIfNotRequestedYet()
  }

  private fun loadProfileIfNotRequestedYet() {
    if (loadProfileJob != null) {
      return
    }
    _refreshing.value = true
    loadProfileJob = viewModelScope.launch {
      val profile = profileRepository.getProfile(AuthStorage.userId)
      val profileState = _state.value?.profileToState(profile) ?: ProfileState.empty().profileToState(profile)
      _state.value = profileState
      _refreshing.value = false
    }
  }

  fun waitForData(onLoaded: (ProfileState) -> Unit) {
    if (waitingFirstDataJob != null) {
      return
    }
    waitingFirstDataJob = viewModelScope.launch {
      _state.filterNotNull().collect {
        onLoaded.invoke(it)
      }
    }
  }

  private suspend fun loadDataForCategory(chosenCategory: Category) {
    when (chosenCategory) {
      POSTS -> {
        val posts = userPostsRepository.getUsersPosts(AuthStorage.userId)
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

  fun updateUser(userName: String, email: String, description: String, onUpdated: () -> Unit) {
    viewModelScope.launch {
      _refreshing.value = true
      profileRepository.updateProfile(userName, email, description)
      _refreshing.value = false
      onUpdated.invoke()
    }
  }

  fun updateAvatar(photoUrl: String) {
    viewModelScope.launch {
      _refreshing.value = true
      profileRepository.updateUserPhoto(photoUrl)
      loadData()
      _refreshing.value = false
    }
  }

  override fun onCleared() {
    super.onCleared()
    loadProfileJob?.cancel()
    loadProfileJob = null
  }
}

data class ProfileState(
  val userName: String,
  val avatarUrl: String,
  val email: String?,
  val description: String,
  val postsCount: Int,
  val followersCount: Int,
  val followingCount: Int,
  val category: Category
) {

  fun profileToState(profile: UserProfile): ProfileState {
    return ProfileState(
      userName = profile.userName,
      avatarUrl = profile.avatarUrl ?: avatarDefault,
      email = profile.email,
      description = profile.description,
      postsCount = profile.postsCount.toInt(),
      followingCount = profile.followingCount.toInt(),
      followersCount = profile.followersCount.toInt(),
      category = category
    )
  }

  companion object {
    fun empty() = ProfileState(
      "", "", "", "", 0, 0, 0, POSTS
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