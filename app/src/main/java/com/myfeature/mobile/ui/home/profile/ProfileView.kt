package com.myfeature.mobile.ui.home.profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.myfeature.mobile.ui.home.profile.model.Category
import com.myfeature.mobile.ui.home.profile.model.Category.FOLLOWERS
import com.myfeature.mobile.ui.home.profile.model.Category.FOLLOWING
import com.myfeature.mobile.ui.home.profile.model.Category.POSTS
import com.myfeature.mobile.ui.home.profile.model.PostItem

val topBarShape = RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp, topStart = 0.dp, topEnd = 0.dp)

private val DEFAULT_PRESELECTED_CATEGORY = POSTS

@Composable
fun ProfileView(modifier: Modifier = Modifier) {
  val selectedCategory = remember { mutableStateOf(DEFAULT_PRESELECTED_CATEGORY) }
  fun onSelectedCategoryChanged(category: Category) {
    selectedCategory.value = category
  }

  LazyColumn(modifier = modifier) {
    item {
      ProfileHeaderView(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .shadow(5.dp, shape = topBarShape)
        .padding(bottom = 5.dp)
        .clip(shape = topBarShape), onPhotoChangeClick = { /* TODO */ })
    }
    item {
      CategoriesLikeViewPagerView(selected = selectedCategory)
    }
    when (selectedCategory.value) {
      POSTS -> {
        buildPostsList(emptyList())
      }
      FOLLOWERS -> { /*TODO*/
      }
      FOLLOWING -> { /*TODO*/
      }
    }
  }
}

fun LazyListScope.buildPostsList(posts: List<PostItem>) {
  repeat(5) {
    item {
      Text("Post")
    }
  }
}

@Preview
@Composable
fun ProfilePreview() {
  ProfileView()
}