package com.myfeature.mobile.ui.beginner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.myfeature.mobile.core.theme.BlueGreen
import com.myfeature.mobile.core.theme.White
import com.myfeature.mobile.core.utils.Functions
import com.myfeature.mobile.ui.common.Logo

private val paddingButtons = 4.dp

@Composable
fun LoadingView(
  modifier: Modifier = Modifier,
  onLoggedIn: () -> Unit = Functions::empty,
  onNeedToAuth: () -> Unit = Functions::empty
) {
  ConstraintLayout(
    modifier = modifier
      .background(
        brush = Brush.verticalGradient(
          colors = listOf(BlueGreen, White),
          endY = 600f
        )
      )
      .fillMaxSize()
  ) {
    val (progress, logo) = createRefs()

    onLoggedIn.invoke()
    CircularProgressIndicator(
      modifier = modifier
        .constrainAs(progress) {
          top.linkTo(parent.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
          bottom.linkTo(parent.bottom)
        }
        .wrapContentSize(align = Alignment.Center, unbounded = true)
    )
    Logo(
      modifier = Modifier
        .constrainAs(logo) {
          bottom.linkTo(progress.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }
        .padding(bottom = 40.dp)
    )
  }
}

@Preview
@Composable
fun PreviewLoadingVIew() {
  LoadingView()
}