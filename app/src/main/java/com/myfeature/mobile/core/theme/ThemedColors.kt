package com.myfeature.mobile.core.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color

val Colors.barBackground: Color
  get() = White

val Colors.controlMain: Color
  get() = ControlColor

val Colors.textMain: Color
  get() = TextColor

val Colors.textMinor: Color
  get() = TextMinorColor

// TODO: Fix it with dark theme
val Colors.bgMain: Color
  get() = if (isLight) LightBlueberry else LightBlueberry