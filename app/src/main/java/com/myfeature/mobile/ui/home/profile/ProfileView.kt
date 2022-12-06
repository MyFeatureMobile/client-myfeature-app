package com.myfeature.mobile.ui.home.profile

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.myfeature.mobile.core.utils.Functions
import com.myfeature.mobile.ui.common.ScrollableTextField
import com.myfeature.mobile.ui.home.common.PostItemView
import com.myfeature.mobile.ui.home.profile.ProfileContent.Empty
import com.myfeature.mobile.ui.home.profile.ProfileContent.FollowersContent
import com.myfeature.mobile.ui.home.profile.ProfileContent.FollowingContent
import com.myfeature.mobile.ui.home.profile.ProfileContent.PostsContent
import com.myfeature.mobile.ui.home.profile.header.ProfileHeaderView
import com.myfeature.mobile.ui.home.profile.model.Category.POSTS
import com.myfeature.mobile.ui.home.profile.model.PostItem
import org.koin.androidx.compose.koinViewModel

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
  val profileViewModel: ProfileViewModel = koinViewModel()
  val state = profileViewModel.profileState.collectAsState(initial = null)
  val content = profileViewModel.contentState.collectAsState(initial = Empty())
  val refreshing by profileViewModel.refreshing.collectAsState()
  val addPhotoUrlDialogShowing = remember { mutableStateOf(false) }

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
            onPhotoChangeClick = { addPhotoUrlDialogShowing.value = true },
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
  val photoUrlInput = remember { mutableStateOf("") }
  if (addPhotoUrlDialogShowing.value) {
    AlertDialog(
      onDismissRequest = {
        addPhotoUrlDialogShowing.value = false
      },
      title = {
        Text(text = "Write photo url")
      },
      text = {
        ScrollableTextField(
          modifier = Modifier.padding(top = 6.dp),
          value = photoUrlInput.value,
          onValueChange = { photoUrlInput.value = it }
        )
      },
      confirmButton = {
        Button(
          onClick = {
            profileViewModel.updateAvatar(photoUrl = photoUrlInput.value)
            addPhotoUrlDialogShowing.value = false
          }) {
          Text(text = "Done")
        }
      },
      dismissButton = {
        TextButton(onClick = {
          addPhotoUrlDialogShowing.value = false
        }) {
          Text(text = "Cancel", fontWeight = FontWeight.Normal)
        }
      }
    )
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