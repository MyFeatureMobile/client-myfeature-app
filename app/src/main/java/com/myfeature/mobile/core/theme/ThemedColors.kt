package com.myfeature.mobile.core.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

val Colors.backgroundBottomBar: Color
  get() = White

val Colors.controlMain: Color
  get() = primary

// TODO: Fix it with dark theme
val Colors.bgMain: Color
  get() = if (isLight) LightBlueberry else LightBlueberry