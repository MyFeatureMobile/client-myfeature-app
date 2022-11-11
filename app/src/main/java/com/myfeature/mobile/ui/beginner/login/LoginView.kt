package com.myfeature.mobile.ui.beginner.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.ImeAction.Companion
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.myfeature.mobile.R
import com.myfeature.mobile.core.theme.ControlColor
import com.myfeature.mobile.core.theme.TextColor
import com.myfeature.mobile.core.theme.TextFieldHint
import com.myfeature.mobile.core.theme.White
import com.myfeature.mobile.core.theme.featTextFieldColors
import com.myfeature.mobile.core.utils.annotatedStringResource
import com.myfeature.mobile.core.utils.fontDimensionResource
import com.myfeature.mobile.core.utils.modifierMaxWidth
import com.myfeature.mobile.ui.beginner.Logo
import com.myfeature.mobile.ui.common.ScrollableTextField

@Composable
fun LoginView(
  modifier: Modifier = Modifier,
  onLogInClick: () -> Unit,
  onGoSignIn: () -> Unit,
  onRestoreAccess: () -> Unit,
) {
  val usernameState = remember { mutableStateOf("") }
  val passwordState = remember { mutableStateOf("") }
  val openDialog = remember { mutableStateOf(false) }
  val focusManager = LocalFocusManager.current
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
      ScrollableTextField(
        modifier = modifierMaxWidth
          .padding(vertical = 6.dp),
        value = usernameState.value,
        singleLine = true,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        label = { Text(stringResource(R.string.login_email_username_hint), color = TextFieldHint) },
        onValueChange = { usernameState.value = it },
        colors = featTextFieldColors()
      )
      ScrollableTextField(
        modifier = modifierMaxWidth
          .padding(vertical = 6.dp),
        value = passwordState.value,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
          keyboardType = KeyboardType.Password,
          imeAction = Companion.Done
        ),
        keyboardActions = KeyboardActions(
          onDone = {
            focusManager.clearFocus()
          }
        ),
        visualTransformation = PasswordVisualTransformation(),
        label = { Text(text = stringResource(R.string.login_password_hint), color = TextFieldHint) },
        onValueChange = { passwordState.value = it },
        colors = featTextFieldColors()
      )
      Button(
        modifier = modifierMaxWidth
          .padding(vertical = 12.dp)
          .wrapContentHeight(),
        onClick = {
          openDialog.value = true
          onLogInClick.invoke()
        },
        colors = ButtonDefaults.textButtonColors(
          backgroundColor = ControlColor,
          contentColor = White
        ),
      ) {
        Text(
          text = stringResource(R.string.login_button),
          textAlign = TextAlign.Center,
          modifier = modifierMaxWidth
            .padding(vertical = dimensionResource(R.dimen.button_padding))
        )
      }
      TextButton(
        onClick = onRestoreAccess::invoke,
        modifier = modifierMaxWidth
      ) {
        Text(
          text = annotatedStringResource(R.string.login_forgot_details),
          fontSize = fontDimensionResource(R.dimen.minor_action_button_size),
          textAlign = TextAlign.Center,
          fontWeight = Normal,
          color = TextColor
        )
      }
      Text(
        text = "OR",
        textAlign = TextAlign.Center,
        modifier = modifierMaxWidth
          .padding(8.dp)
      )
      TextButton(
        onClick = onGoSignIn::invoke,
        modifier = modifierMaxWidth,
      ) {
        Text(
          text = annotatedStringResource(R.string.login_create_account_button),
          textAlign = TextAlign.Center,
          fontSize = fontDimensionResource(R.dimen.minor_action_button_size),
          fontWeight = Normal,
          color = TextColor
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
  if (openDialog.value) {
    AlertDialog(
      onDismissRequest = {
        // Dismiss the dialog when the user clicks outside the dialog or on the back
        // button. If you want to disable that functionality, simply use an empty
        // onCloseRequest.
        openDialog.value = false
      },
      title = {
        Text(text = stringResource(id = R.string.login_incorrect_password_title))
      },
      text = {
        Text(text = stringResource(id = R.string.login_incorrect_password_body))
      },
      confirmButton = {
        Button(
          onClick = {
            openDialog.value = false
          }) {
          Text(text = stringResource(id = R.string.login_incorrect_password_dismiss))
        }
      }
    )
  }
}