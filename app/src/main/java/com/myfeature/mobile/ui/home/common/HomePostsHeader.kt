package com.myfeature.mobile.ui.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.myfeature.mobile.R.drawable
import com.myfeature.mobile.core.utils.Functions

@Composable
fun HomePostsHeader(
  modifier: Modifier = Modifier,
  label: String? = null,
  onAddPostClick: () -> Unit = Functions::empty
) {
  ConstraintLayout(
    modifier = modifier
      .wrapContentHeight()
      .fillMaxWidth()
  ) {
    val (button, logo, des) = createRefs()
    Image(
      painter = painterResource(id = drawable.ic_logo),
      contentDescription = "",
      contentScale = ContentScale.Fit,
      modifier = Modifier
        .constrainAs(logo) {
          top.linkTo(parent.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }
        .width(134.dp)
        .padding(top = 16.dp)
    )
    label?.let {
      Text(
        text = it,
        fontSize = 10.sp,
        modifier = Modifier
          .constrainAs(des) {
            top.linkTo(logo.bottom)
            start.linkTo(logo.start)
            end.linkTo(logo.end)
          }
          .padding(bottom = 16.dp)
      )
    }
    Icon(imageVector = Outlined.Add,
      contentDescription = "Add new post",
      modifier = Modifier
        .constrainAs(button) {
          start.linkTo(parent.start)
          top.linkTo(parent.top)
        }
        .padding(top = 16.dp, start = 16.dp)
        .clickable(
          interactionSource = remember { MutableInteractionSource() },
          indication = rememberRipple(bounded = false, radius = 20.dp),
          onClick = { onAddPostClick.invoke() }
        )
    )
  }
}