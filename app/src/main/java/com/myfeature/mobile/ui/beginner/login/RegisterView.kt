package com.myfeature.mobile.ui.beginner.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.myfeature.mobile.R.dimen
import com.myfeature.mobile.core.theme.ControlColor
import com.myfeature.mobile.core.theme.ControlDisabledColor
import com.myfeature.mobile.core.theme.TextFieldHint
import com.myfeature.mobile.core.theme.White
import com.myfeature.mobile.core.theme.featTextFieldColors
import com.myfeature.mobile.core.utils.modifierMaxWidth
import com.myfeature.mobile.ui.Logo
import com.myfeature.mobile.ui.common.ScrollableTextField

@Composable
fun RegisterView(
  modifier: Modifier = Modifier,
  onInputFinished: (String, String) -> Unit
) {
  val userName = remember { mutableStateOf("") }
  val requestUserName = remember { mutableStateOf(true) }
  ConstraintLayout(
    modifier = modifier
      .fillMaxSize()
      .padding(32.dp)
  ) {
    val (inputForm, logo) = createRefs()
    val formModifier = Modifier
      .constrainAs(inputForm) {
        top.linkTo(logo.bottom)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
      }
      .padding(top = 16.dp)
    if (requestUserName.value) {
      SingleForm(
        modifier = formModifier,
        title = "Choose username",
        subtitle = "You can always change it later",
        hint = "Username",
        buttonText = "Next",
        onDone = {
          userName.value = it
          requestUserName.value = false
        }
      )
    } else {
      SingleForm(
        modifier = formModifier,
        title = "Create a password",
        subtitle = "You can always change it later",
        hint = "Password",
        buttonText = "Next",
        onDone = { password ->
          onInputFinished.invoke(userName.value, password)
        },
        transformation = PasswordVisualTransformation()
      )
    }
    Logo(
      modifier = Modifier
        .constrainAs(logo) {
          bottom.linkTo(inputForm.top)
          start.linkTo(parent.start)
          end.linkTo(parent.end)
          top.linkTo(parent.top, margin = 120.dp)
        }
        .padding(bottom = 40.dp)
    )
  }
}

@Composable
fun SingleForm(
  modifier: Modifier = Modifier,
  title: String,
  subtitle: String,
  hint: String,
  onDone: (String) -> Unit,
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  buttonText: String,
  transformation: VisualTransformation = VisualTransformation.None,
) {
  val focusRequester = remember { FocusRequester() }
  val fieldState = remember { mutableStateOf("") }
  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = title,
      fontSize = 24.sp,
      textAlign = TextAlign.Center
    )
    Text(
      text = subtitle,
      textAlign = TextAlign.Center
    )
    ScrollableTextField(
      modifier = modifierMaxWidth
        .padding(vertical = 6.dp)
        .focusRequester(focusRequester),
      value = fieldState.value,
      singleLine = true,
      keyboardOptions = keyboardOptions,
      visualTransformation = transformation,
      label = { Text(text = hint, color = TextFieldHint) },
      onValueChange = { fieldState.value = it },
      colors = featTextFieldColors()
    )
    Button(
      modifier = modifierMaxWidth
        .padding(vertical = 12.dp)
        .wrapContentHeight(),
      onClick = {
        onDone.invoke(fieldState.value)
      },
      colors = ButtonDefaults.buttonColors(
        backgroundColor = ControlColor,
        contentColor = White,
        disabledBackgroundColor = ControlDisabledColor,
        disabledContentColor = White
      ),
      enabled = fieldState.value.isNotBlank()
    ) {
      Text(
        text = buttonText,
        textAlign = TextAlign.Center,
        modifier = modifierMaxWidth
          .padding(vertical = dimensionResource(dimen.button_padding))
      )
    }
  }
  SideEffect {
    focusRequester.requestFocus()
  }
}

@Preview
@Composable
fun PreviewRegisterView() {
  RegisterView(onInputFinished = { _, _ -> })
}