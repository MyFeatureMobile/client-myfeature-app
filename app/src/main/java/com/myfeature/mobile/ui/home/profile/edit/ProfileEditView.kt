package com.myfeature.mobile.ui.home.profile.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.myfeature.mobile.R.dimen
import com.myfeature.mobile.core.theme.ControlColor
import com.myfeature.mobile.core.theme.ControlDisabledColor
import com.myfeature.mobile.core.theme.TextColor
import com.myfeature.mobile.core.theme.TextFieldHint
import com.myfeature.mobile.core.theme.White
import com.myfeature.mobile.core.theme.featTextFieldColors
import com.myfeature.mobile.core.utils.fontDimensionResource
import com.myfeature.mobile.core.utils.modifierMaxWidth
import com.myfeature.mobile.ui.common.ScrollableTextField
import com.myfeature.mobile.ui.home.profile.ProfileViewModel
import com.myfeature.mobile.ui.home.profile.header.AvatarPhotoView

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProfileEditView(modifier: Modifier = Modifier, onUpdated: () -> Unit, onCancel: () -> Unit) {
  val viewModel: ProfileViewModel = viewModel()
  val profile = viewModel.profileState.collectAsState(initial = null).value
  val refreshing = viewModel.refreshing.collectAsState()
  val focusRequester = remember { FocusRequester() }

  val fieldNameState = remember { mutableStateOf(profile?.userName ?: "") }
  val fieldEmailState = remember { mutableStateOf(profile?.email ?: "") }
  val fieldDescriptionState = remember { mutableStateOf(profile?.description ?: "") }

  val keyboardController = LocalSoftwareKeyboardController.current

  viewModel.waitForData {
    fieldNameState.value = it.userName
    fieldEmailState.value = it.email ?: ""
    fieldDescriptionState.value = it.description
  }

  SwipeRefresh(
    state = rememberSwipeRefreshState(isRefreshing = refreshing.value),
    onRefresh = { /* Nothing to do */ }
  ) {
    Column(
      modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
      if (profile != null) {
        AvatarPhotoView(photoSize = 100.dp, iconSize = 25.dp, photoUrl = profile.avatarUrl)
        Text(
          modifier = Modifier.padding(top = 8.dp),
          text = fieldNameState.value,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold,
          color = TextColor
        )
        ScrollableTextField(
          modifier = modifierMaxWidth
            .padding(vertical = 6.dp)
            .focusRequester(focusRequester),
          value = fieldNameState.value,
          singleLine = true,
          label = { Text(text = "New username", color = TextFieldHint) },
          onValueChange = { fieldNameState.value = it },
          colors = featTextFieldColors(),
          enabled = !refreshing.value
        )
        ScrollableTextField(
          modifier = modifierMaxWidth
            .padding(vertical = 6.dp)
            .focusRequester(focusRequester),
          value = fieldDescriptionState.value,
          singleLine = false,
          label = { Text(text = "New description", color = TextFieldHint) },
          onValueChange = { fieldDescriptionState.value = it },
          colors = featTextFieldColors(),
          enabled = !refreshing.value
        )
        ScrollableTextField(
          modifier = modifierMaxWidth
            .padding(vertical = 6.dp)
            .focusRequester(focusRequester),
          value = fieldEmailState.value,
          singleLine = true,
          label = { Text(text = "New email", color = TextFieldHint) },
          onValueChange = { fieldEmailState.value = it },
          colors = featTextFieldColors(),
          enabled = !refreshing.value
        )
        Button(
          modifier = modifierMaxWidth
            .padding(top = 12.dp)
            .wrapContentHeight(),
          onClick = {
            keyboardController?.hide()
            viewModel.updateUser(fieldNameState.value, fieldEmailState.value, fieldDescriptionState.value, onUpdated)
          },
          colors = ButtonDefaults.buttonColors(
            backgroundColor = ControlColor,
            contentColor = White,
            disabledBackgroundColor = ControlDisabledColor,
            disabledContentColor = White
          ),
          enabled = !refreshing.value
        ) {
          Text(
            text = "Save",
            textAlign = TextAlign.Center,
            modifier = modifierMaxWidth
              .padding(vertical = dimensionResource(dimen.button_padding))
          )
        }
        TextButton(
          onClick = {
            keyboardController?.hide()
            onCancel.invoke()
          },
          modifier = modifierMaxWidth.padding(top = 6.dp)
        ) {
          Text(
            text = "Cancel",
            fontSize = fontDimensionResource(dimen.minor_action_button_size),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            color = TextColor
          )
        }
      }
    }
  }
}