package com.myfeature.mobile.ui.beginner.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.myfeature.mobile.R
import com.myfeature.mobile.R.dimen
import com.myfeature.mobile.core.utils.Functions
import com.myfeature.mobile.core.utils.modifierMaxWidth
import com.myfeature.mobile.ui.common.Logo

@Composable
fun FinishRegisterView(
  modifier: Modifier = Modifier,
  userName: String = "",
  onCompleteRegister: () -> Unit = Functions::empty
) {
  ConstraintLayout(
    modifier = modifier
      .fillMaxSize()
      .padding(32.dp)
  ) {
    val (inputForm, logo) = createRefs()
    Column(
      modifier = modifierMaxWidth
        .constrainAs(inputForm) {
          top.linkTo(parent.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
          bottom.linkTo(parent.bottom)
        }
    ) {
      Text(
        modifier = modifierMaxWidth
          .padding(vertical = 6.dp),
        text = stringResource(id = R.string.login_welcome_to_my_feature_title, userName),
        fontSize = 20.sp,
        textAlign = TextAlign.Center
      )
      Button(
        modifier = modifierMaxWidth
          .padding(vertical = 12.dp)
          .wrapContentHeight(),
        onClick = {
          onCompleteRegister.invoke()
        }
      ) {
        Text(
          text = "Complete Sign-up",
          textAlign = TextAlign.Center,
          modifier = modifierMaxWidth
            .padding(vertical = dimensionResource(dimen.button_padding))
        )
      }
    }
    Logo(
      modifier = Modifier
        .constrainAs(logo) {
          bottom.linkTo(inputForm.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
        }
        .padding(bottom = 40.dp)
    )
  }
}

@Preview
@Composable
fun FinishRegisterView() {
  FinishRegisterView(userName = "klmn_s")
}