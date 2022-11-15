package com.myfeature.mobile.ui.home.post

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.SubcomposeAsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.myfeature.mobile.core.theme.barBackground
import com.myfeature.mobile.core.theme.controlMain
import com.myfeature.mobile.data.TestData
import com.myfeature.mobile.ui.home.post.model.PostModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PostNamePhotosView(
  modifier: Modifier = Modifier,
  postState: MutableState<PostModel> = remember { mutableStateOf(TestData.post) }
) {
  val post = postState.value
  ConstraintLayout(
    modifier = modifier
      .background(color = MaterialTheme.colors.barBackground, shape = RoundedCornerShape(15.dp))
      .padding(vertical = 8.dp, horizontal = 6.dp)
  ) {
    val (userPhoto, userName, postPhotos, likeIcon, likes) = createRefs()
    Image(
      painter = rememberAsyncImagePainter(post.userOnPost.userPhotoUrl),
      contentDescription = "Your photo",
      modifier = Modifier
        .size(34.dp)
        .clip(CircleShape)
        .constrainAs(userPhoto) {
          top.linkTo(parent.top)
          start.linkTo(parent.start)
          bottom.linkTo(postPhotos.top)
        },
      contentScale = ContentScale.Crop
    )
    Text(
      text = post.userOnPost.nickname,
      fontSize = 11.sp,
      fontWeight = FontWeight.Bold,
      modifier = Modifier
        .constrainAs(userName) {
          top.linkTo(userPhoto.top)
          start.linkTo(userPhoto.end)
          bottom.linkTo(userPhoto.bottom)
        }
        .padding(start = 6.dp))
    HorizontalPager(
      count = post.photoUrls.size,
      modifier = Modifier
        .padding(vertical = 8.dp)
        .clip(RoundedCornerShape(15.dp))
        .constrainAs(postPhotos) {
          top.linkTo(userPhoto.bottom)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
          width = Dimension.fillToConstraints
          height = Dimension.wrapContent
        }
    ) { page ->
      SubcomposeAsyncImage(
        model = post.photoUrls[page],
        loading = {
          CircularProgressIndicator(
            modifier = modifier
              .size(32.dp)
              .align(alignment = Alignment.Center)
          )
        },
        contentDescription = "post",
        modifier = modifier
          .fillMaxWidth()
          .clip(RoundedCornerShape(size = 15.dp)),
        contentScale = ContentScale.FillWidth
      )
    }
    if (post.liked) {
      Icon(
        imageVector = Filled.Favorite,
        contentDescription = "Like",
        tint = MaterialTheme.colors.controlMain,
        modifier = Modifier
          .padding(horizontal = 6.dp)
          .size(24.dp)
          .constrainAs(likeIcon) {
            top.linkTo(postPhotos.bottom)
            start.linkTo(postPhotos.start)
          }
          .clickable { postState.value = postState.value.copy(liked = false) },
      )
    } else {
      Icon(
        imageVector = Outlined.FavoriteBorder,
        contentDescription = "Like",
        modifier = Modifier
          .padding(horizontal = 6.dp)
          .size(24.dp)
          .constrainAs(likeIcon) {
            top.linkTo(postPhotos.bottom)
            start.linkTo(postPhotos.start)
          }
          .clickable { postState.value = postState.value.copy(liked = true) },
      )
    }
  }
}

@Preview
@Composable
fun PostNamePhotoPreview() {
  PostNamePhotosView()
}