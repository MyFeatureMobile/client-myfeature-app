package com.myfeature.mobile.ui.home.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.myfeature.mobile.core.utils.Functions
import com.myfeature.mobile.ui.home.common.PostItemView
import com.myfeature.mobile.ui.home.profile.ProfileContent.Empty
import com.myfeature.mobile.ui.home.profile.ProfileContent.FollowersContent
import com.myfeature.mobile.ui.home.profile.ProfileContent.FollowingContent
import com.myfeature.mobile.ui.home.profile.ProfileContent.PostsContent
import com.myfeature.mobile.ui.home.profile.header.ProfileHeaderView
import com.myfeature.mobile.ui.home.profile.model.Category.POSTS
import com.myfeature.mobile.ui.home.profile.model.PostItem

val topBarShape = RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp, topStart = 0.dp, topEnd = 0.dp)
val DEFAULT_PRESELECTED_CATEGORY = POSTS
private const val PROFILE_KEY = "profile"

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProfileView(
  modifier: Modifier = Modifier,
  onLogOut: () -> Unit = Functions::empty,
  onEditProfile: () -> Unit = Functions::empty
) {
  val profileViewModel: ProfileViewModel = viewModel()
  val state = profileViewModel.profileState.collectAsState(initial = null)
  val content = profileViewModel.contentState.collectAsState(initial = Empty())
  val refreshing by profileViewModel.refreshing.collectAsState()

  SwipeRefresh(
    state = rememberSwipeRefreshState(isRefreshing = refreshing),
    onRefresh = { profileViewModel.loadData() }
  ) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
      val stateValue = state.value
      if (stateValue != null) {
        item(key = PROFILE_KEY) {
          ProfileHeaderView(
            modifier = Modifier
              .animateItemPlacement()
              .fillMaxWidth()
              .wrapContentHeight()
              .shadow(5.dp, shape = topBarShape)
              .padding(bottom = 5.dp)
              .clip(shape = topBarShape),
            profileState = stateValue,
            onEditProfile = onEditProfile,
            onPhotoChangeClick = { /* TODO */ },
            onLogOut = {
              profileViewModel.logOut()
              onLogOut.invoke()
            }
          )
        }
      }
      item {
        CategoriesLikeViewPagerView(
          selected = content.value.category,
          onChangeSelection = { profileViewModel.selectCategory(it) })
      }
      when (val type = content.value) {
        is Empty -> { /* TODO */
        }
        is PostsContent -> {
          buildPostsList(posts = type.posts, modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp))
        }
        is FollowersContent -> { /*TODO*/
        }
        is FollowingContent -> { /*TODO*/
        }
      }
    }
  }
}

fun LazyListScope.buildPostsList(posts: List<PostItem>, modifier: Modifier = Modifier) {
  posts.forEach {
    item {
      PostItemView(modifier = modifier, postItem = it, onItemClick = { /* TODO */ })
    }
  }
}

@Preview
@Composable
fun ProfilePreview() {
  ProfileView()
}