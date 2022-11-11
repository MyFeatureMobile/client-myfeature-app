package com.myfeature.mobile.ui.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.myfeature.mobile.R
import com.myfeature.mobile.ui.home.profile.model.PostItem

@Composable
fun PostItemView(modifier: Modifier = Modifier, postItem: PostItem, onItemClick: (PostItem) -> Unit) {
  Image(
    painter = rememberAsyncImagePainter(
      model = postItem.photoUrls.firstOrNull(),
//      placeholder = R.drawable.
    ),
    contentDescription = "Your photo",
    modifier = modifier
      .size(72.dp)
      .clip(RoundedCornerShape(size = 15.dp)),
    contentScale = ContentScale.Crop
  )
}