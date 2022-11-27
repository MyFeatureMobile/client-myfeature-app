package com.myfeature.mobile.ui.home.post.create

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.myfeature.mobile.R.dimen
import com.myfeature.mobile.core.theme.ControlColor
import com.myfeature.mobile.core.theme.ControlDisabledColor
import com.myfeature.mobile.core.theme.TextColor
import com.myfeature.mobile.core.theme.TextFieldHint
import com.myfeature.mobile.core.theme.TextMinorColor
import com.myfeature.mobile.core.theme.White
import com.myfeature.mobile.core.theme.featTextFieldColors
import com.myfeature.mobile.core.utils.Functions
import com.myfeature.mobile.core.utils.fontDimensionResource
import com.myfeature.mobile.core.utils.modifierMaxWidth
import com.myfeature.mobile.ui.common.ScrollableTextField
import com.myfeature.mobile.ui.home.common.HomePostsHeader

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PostCreateView(modifier: Modifier = Modifier, onCompleted: () -> Unit = Functions::empty) {
  val viewModel: PostCreateViewModel = viewModel()
  val inputState = viewModel.inputState.collectAsState()
  val addPhotoUrlDialogShowing = remember { mutableStateOf(false) }
  val focusRequester = remember { FocusRequester() }

  val creating = remember { mutableStateOf(false) }
  val keyboardController = LocalSoftwareKeyboardController.current

  val photoUrlInput = remember { mutableStateOf("") }

  Column(modifier = modifier.padding(16.dp)) {
    HomePostsHeader(
      modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight(),
      showAddPostButton = false
    )
    Button(
      modifier = Modifier
        .fillMaxWidth()
        .padding(top = 16.dp)
        .wrapContentHeight(),
      onClick = { addPhotoUrlDialogShowing.value = true },
      colors = ButtonDefaults.buttonColors(
        backgroundColor = White,
        contentColor = ControlColor,
        disabledBackgroundColor = White,
        disabledContentColor = ControlDisabledColor
      )
    ) {
      Column(modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
          imageVector = Filled.Add,
          contentDescription = "Add photo",
          modifier = Modifier
            .padding(4.dp)
            .size(72.dp),
        )
        Text(
          text = "Add photo",
          modifier = modifierMaxWidth
            .padding(bottom = 6.dp),
          fontSize = 8.sp,
          fontWeight = FontWeight.Light,
          textAlign = TextAlign.Center,
          color = TextMinorColor
        )
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(White),
          horizontalArrangement = Arrangement.Start
        ) {
          inputState.value.photoUrl.forEach { photoUrl ->
            Image(
              painter = rememberAsyncImagePainter(photoUrl),
              contentDescription = "Your photo",
              modifier = modifier
                .background(White)
                .padding(end = 4.dp)
                .size(44.dp)
                .clip(RoundedCornerShape(15.dp)),
              contentScale = ContentScale.Crop
            )
          }
        }
      }
    }
    ScrollableTextField(
      modifier = modifierMaxWidth
        .padding(vertical = 6.dp)
        .focusRequester(focusRequester),
      value = inputState.value.description,
      singleLine = true,
      label = { Text(text = "Add description", color = TextFieldHint) },
      onValueChange = { viewModel.changeDescription(it) },
      colors = featTextFieldColors(),
      enabled = !creating.value
    )
    ScrollableTextField(
      modifier = modifierMaxWidth
        .padding(vertical = 6.dp)
        .focusRequester(focusRequester),
      value = inputState.value.githubLink,
      singleLine = false,
      label = { Text(text = "GitHub link..", color = TextFieldHint) },
      onValueChange = { viewModel.changeGitHubLink(it) },
      colors = featTextFieldColors(),
      enabled = !creating.value
    )
    ScrollableTextField(
      modifier = modifierMaxWidth
        .padding(vertical = 6.dp)
        .focusRequester(focusRequester),
      value = inputState.value.code,
      singleLine = true,
      label = { Text(text = "Or write code here", color = TextFieldHint) },
      onValueChange = { viewModel.updateCode(it) },
      colors = featTextFieldColors(),
      enabled = !creating.value
    )
    Button(
      modifier = modifierMaxWidth
        .wrapContentHeight(),
      onClick = {
        keyboardController?.hide()
        creating.value = true
        viewModel.createPost {
          onCompleted.invoke()
        }
      },
      colors = ButtonDefaults.buttonColors(
        backgroundColor = ControlColor,
        contentColor = White,
        disabledBackgroundColor = ControlDisabledColor,
        disabledContentColor = White
      ),
      enabled = !creating.value
    ) {
      if (creating.value) {
        CircularProgressIndicator(
          modifier = Modifier
            .size(28.dp)
            .align(CenterVertically),
          strokeWidth = 2.dp,
          color = White
        )
      } else {
        Text(
          text = "Save",
          textAlign = TextAlign.Center,
          modifier = modifierMaxWidth
            .padding(vertical = dimensionResource(dimen.button_padding))
        )
      }
    }
    TextButton(
      onClick = {
        keyboardController?.hide()
        onCompleted.invoke()
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

  if (addPhotoUrlDialogShowing.value) {
    AlertDialog(
      onDismissRequest = {
        addPhotoUrlDialogShowing.value = false
      },
      title = {
        Text(text = "Write photo url")
      },
      text = {
        ScrollableTextField(
          modifier = Modifier.padding(top = 6.dp),
          value = photoUrlInput.value,
          onValueChange = { photoUrlInput.value = it }
        )
      },
      confirmButton = {
        Button(
          onClick = {
            viewModel.updatePhotoUrl(photoUrl = photoUrlInput.value)
            addPhotoUrlDialogShowing.value = false
          }) {
          Text(text = "Done")
        }
      },
      dismissButton = {
        TextButton(onClick = {
          addPhotoUrlDialogShowing.value = false
        }) {
          Text(text = "Cancel", fontWeight = FontWeight.Normal)
        }
      }
    )
  }
}

@Preview
@Composable
fun PostCreatePreview() {
  PostCreateView()
}