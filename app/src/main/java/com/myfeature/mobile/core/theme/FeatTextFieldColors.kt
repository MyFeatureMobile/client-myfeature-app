package com.myfeature.mobile.core.theme

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black

@Composable
fun featTextFieldColors(): TextFieldColors {
  return TextFieldDefaults.textFieldColors(
    textColor = Black,
    disabledTextColor = TextFieldHint,
    backgroundColor = TextFieldBackground,
    placeholderColor = TextFieldHint,
    disabledLabelColor = TextFieldHint,
    unfocusedLabelColor = TextFieldHint,
    focusedLabelColor = TextFieldHint
  )
}