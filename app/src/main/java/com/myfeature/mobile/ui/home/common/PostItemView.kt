package com.myfeature.mobile.ui.home.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.myfeature.mobile.ui.home.profile.model.PostItem

@Composable
fun PostItemView(modifier: Modifier = Modifier, postItem: PostItem, onItemClick: (PostItem) -> Unit) {
  SubcomposeAsyncImage(
    model = postItem.photoUrls.firstOrNull(),
    loading = {
      CircularProgressIndicator()
    },
    contentDescription = "Your photo",
    modifier = modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .clip(RoundedCornerShape(size = 15.dp))
      .clickable { onItemClick.invoke(postItem) },
    contentScale = ContentScale.Crop
  )
}