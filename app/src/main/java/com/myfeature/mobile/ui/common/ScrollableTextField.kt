package com.myfeature.mobile.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.text.input.VisualTransformation
import com.myfeature.mobile.core.theme.featTextFieldColors
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollableTextField(
  modifier: Modifier = Modifier,
  value: String = "",
  singleLine: Boolean = false,
  label: @Composable (() -> Unit)? = null,
  onValueChange: ((String) -> Unit)? = null,
  colors: TextFieldColors = featTextFieldColors(),
  keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
  keyboardActions: KeyboardActions = KeyboardActions(),
  visualTransformation: VisualTransformation = VisualTransformation.None
) {
  val bringIntoViewRequester = remember { BringIntoViewRequester() }
  val coroutineScope = rememberCoroutineScope()
  OutlinedTextField(
    value = value,
    singleLine = singleLine,
    label = { label?.invoke() },
    onValueChange = { onValueChange?.invoke(it) },
    colors = colors,
    modifier = modifier
      .bringIntoViewRequester(bringIntoViewRequester)
      .onFocusEvent { focusState ->
        if (focusState.isFocused) {
          coroutineScope.launch {
            bringIntoViewRequester.bringIntoView()
          }
        }
      },
    keyboardActions = keyboardActions,
    keyboardOptions = keyboardOptions,
    visualTransformation = visualTransformation
  )
}