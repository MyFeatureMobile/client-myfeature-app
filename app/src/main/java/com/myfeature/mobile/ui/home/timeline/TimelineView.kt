package com.myfeature.mobile.ui.home.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myfeature.mobile.core.utils.Functions
import com.myfeature.mobile.ui.home.common.HomePostsHeader
import com.myfeature.mobile.ui.home.common.PostItemView
import org.koin.androidx.compose.koinViewModel

@Composable
fun TimelineView(
  modifier: Modifier = Modifier,
  label: String = "Timeline for you ❤️",
  onAddPostClick: () -> Unit = Functions::empty,
  onPostClick: (Long) -> Unit = Functions::empty,
) {
  val viewModel: TimelineViewModel = koinViewModel()
  val posts = viewModel.timeline.collectAsState()
  Box(
    modifier = modifier
      .padding(horizontal = 4.dp)
  ) {
    LazyColumn(
      modifier = Modifier
        .padding(top = 16.dp)
    ) {
      item {
        HomePostsHeader(label = label) {
          onAddPostClick.invoke()
        }
      }
      posts.value.forEach { post ->
        item {
          PostItemView(
            postItem = post,
            onItemClick = { onPostClick.invoke(post.id) },
            modifier = Modifier.padding(top = 8.dp, start = 16.dp, end = 16.dp)
          )
        }
      }
    }
  }
}