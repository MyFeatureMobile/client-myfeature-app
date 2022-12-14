package com.myfeature.mobile.ui.beginner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.myfeature.mobile.core.theme.BlueGreen
import com.myfeature.mobile.core.theme.ControlColor
import com.myfeature.mobile.core.theme.White
import com.myfeature.mobile.core.utils.Functions
import com.myfeature.mobile.ui.common.Logo

private val paddingButtons = 4.dp

@Composable
fun BeginnerView(
  modifier: Modifier = Modifier,
  onLogIn: () -> Unit = Functions::empty,
  onSignUp: () -> Unit = Functions::empty
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
    val (buttonBox, logo) = createRefs()
    Box(
      modifier = modifier
        .constrainAs(buttonBox) {
          top.linkTo(parent.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
          bottom.linkTo(parent.bottom)
        }
        .fillMaxWidth()
        .wrapContentHeight()
    ) {
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
          .fillMaxWidth()
          .align(Alignment.Center)
      ) {
        Button(
          onClick = { onLogIn.invoke() },
          colors = ButtonDefaults.textButtonColors(
            backgroundColor = ControlColor,
            contentColor = White
          ),
          modifier = Modifier
            .width(130.dp)
            .wrapContentHeight()
        ) {
          Text(
            "Login",
            modifier = Modifier.padding(vertical = paddingButtons)
          )
        }
        Button(
          onClick = { onSignUp.invoke() },
          colors = ButtonDefaults.textButtonColors(
            backgroundColor = ControlColor,
            contentColor = White
          ),
          modifier = Modifier
            .width(130.dp)
            .padding(vertical = paddingButtons)
            .wrapContentHeight()
        ) {
          Text(
            "Sign up",
            modifier = Modifier.padding(vertical = paddingButtons)
          )
        }
      }
    }
    Logo(
      modifier = Modifier
        .constrainAs(logo) {
          bottom.linkTo(buttonBox.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }
        .padding(bottom = 40.dp)
    )
  }
}

@Preview
@Composable
fun PreviewBeginner() {
  BeginnerView()
}