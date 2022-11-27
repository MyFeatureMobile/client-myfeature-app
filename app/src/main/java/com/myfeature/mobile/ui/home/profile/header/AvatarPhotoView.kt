package com.myfeature.mobile.ui.home.profile.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.myfeature.mobile.core.theme.bgMain
import com.myfeature.mobile.core.theme.controlMain
import com.myfeature.mobile.core.utils.Functions

@Composable
fun AvatarPhotoView(modifier: Modifier = Modifier, onChangePhoto: () -> Unit = Functions::empty, photoUrl: String) {
  ConstraintLayout(modifier = modifier) {
    val (photo, button) = createRefs()
    Image(
      painter = rememberAsyncImagePainter(photoUrl),
      contentDescription = "Your photo",
      modifier = modifier
        .size(72.dp)
        .clip(CircleShape)
        .constrainAs(photo) {
          top.linkTo(parent.top)
          bottom.linkTo(parent.bottom)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        },
      contentScale = ContentScale.Crop
    )
    IconButton(
      onClick = onChangePhoto,
      enabled = true,
      modifier = Modifier
        .background(MaterialTheme.colors.controlMain, CircleShape)
        .border(width = 1.dp, color = MaterialTheme.colors.bgMain, shape = CircleShape)
        .size(18.dp)
        .constrainAs(button) {
          bottom.linkTo(photo.bottom, margin = 1.dp)
          linkTo(start = photo.start, end = photo.end, bias = 0.8F)
        },
    ) {
      Icon(
        imageVector = Outlined.Edit,
        contentDescription = "Edit your photo",
        tint = MaterialTheme.colors.bgMain,
        modifier = Modifier.size(11.dp)
      )
    }
  }
}

@Preview
@Composable
fun PreviewAvatarView() {
  AvatarPhotoView(photoUrl = PHOTO_EXAMPLE)
}

private const val PHOTO_EXAMPLE =
  "https://i.redd.it/9jn0nbr2pea31.jpg"