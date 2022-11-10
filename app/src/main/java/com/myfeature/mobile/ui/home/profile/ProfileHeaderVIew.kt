package com.myfeature.mobile.ui.home.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.myfeature.mobile.core.theme.barBackground
import com.myfeature.mobile.core.utils.Functions

@Composable
fun ProfileHeaderView(modifier: Modifier = Modifier, onPhotoChangeClick: () -> Unit = Functions::empty) {
  ConstraintLayout(
    modifier = modifier
      .background(MaterialTheme.colors.barBackground)
      .fillMaxWidth()
      .wrapContentHeight()
      .padding(16.dp)
      .clip(RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp, topStart = 0.dp, topEnd = 0.dp))
  ) {
    val (avatar, nameView, info, settings) = createRefs()
    AvatarPhotoView(
      modifier = Modifier
        .constrainAs(avatar) {
          start.linkTo(parent.start)
          bottom.linkTo(parent.bottom)
          top.linkTo(parent.top)
        },
      onChangePhoto = onPhotoChangeClick
    )
    NameInProfileView(
      modifier = Modifier
        .constrainAs(nameView) {
          top.linkTo(avatar.top)
          start.linkTo(avatar.end)
        }
        .padding(start = 8.dp),
      name = "Ivan Kostylev",
      onEditName = { /*TODO*/ })
  }
}

@Preview
@Composable
fun PreviewProfileHeader() {
  ProfileHeaderView()
}