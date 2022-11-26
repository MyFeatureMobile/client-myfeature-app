package com.myfeature.mobile.ui.home.profile.header

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.myfeature.mobile.R
import com.myfeature.mobile.core.theme.barBackground
import com.myfeature.mobile.core.utils.Functions
import com.myfeature.mobile.ui.home.profile.ProfileState

@Composable
fun ProfileHeaderView(
  modifier: Modifier = Modifier,
  profileState: ProfileState = remember { ProfileState.EMPTY() },
  onPhotoChangeClick: () -> Unit = Functions::empty,
  onLogOut: () -> Unit = Functions::empty
) {
  ConstraintLayout(
    modifier
      .background(MaterialTheme.colors.barBackground)
      .fillMaxWidth()
      .padding(16.dp)
      .wrapContentHeight()
  ) {
    val (avatar, nameView, info, settings) = createRefs()
    AvatarPhotoView(
      modifier = Modifier
        .constrainAs(avatar) {
          start.linkTo(parent.start)
          bottom.linkTo(parent.bottom)
          top.linkTo(parent.top)
        }
        .wrapContentSize(),
      photoUrl = profileState.avatarUrl,
      onChangePhoto = onPhotoChangeClick
    )
    ProfileUserNameView(
      modifier = Modifier
        .constrainAs(nameView) {
          bottom.linkTo(info.top)
          start.linkTo(avatar.end)
        }
        .padding(start = 16.dp)
        .wrapContentSize(),
      name = profileState.userName,
      onEditName = { /*TODO*/ })
    Row(
      modifier = Modifier
        .constrainAs(info) {
          bottom.linkTo(avatar.bottom)
          start.linkTo(avatar.end)
          end.linkTo(parent.end)
          width = Dimension.fillToConstraints
        }
        .wrapContentHeight()
        .padding(start = 16.dp, end = 16.dp),
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      ProfileNumberItem(paramName = "Posts", number = profileState.postsCount)
      ProfileNumberItem(paramName = "Followers", number = profileState.followersCount)
      ProfileNumberItem(paramName = "Following", number = profileState.followingCount)
    }
    Icon(
      painter = painterResource(id = R.drawable.ic_logout),
      modifier = Modifier
        .constrainAs(settings) {
          top.linkTo(parent.top)
          end.linkTo(parent.end)
        }
        .size(18.dp)
        .clickable(
          interactionSource = remember { MutableInteractionSource() },
          indication = rememberRipple(bounded = false, radius = 12.dp),
          onClick = { onLogOut.invoke() }
        ),
      contentDescription = "Settings"
    )
  }
}

@Preview
@Composable
fun PreviewProfileHeader() {
  ProfileHeaderView()
}