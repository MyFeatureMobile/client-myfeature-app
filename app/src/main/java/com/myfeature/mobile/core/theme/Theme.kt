package com.myfeature.mobile.core.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@SuppressLint("ConflictingOnColor")
private val DarkColorPalette = darkColors(
  primary = ControlColor,
  primaryVariant = ControlColor,
  secondary = ControlColor,
  background = BackgroundFill,
  onBackground = Color.Black,
  onSecondary = Color.White,
  onPrimary = Color.White
)

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
  primary = ControlColor,
  primaryVariant = ControlColor,
  secondary = ControlColor,
  background = BackgroundFill,
  onBackground = Color.Black,
  onSecondary = Color.White,
  onPrimary = Color.White
  /* Other default colors to override
  background = Color.White,
  surface = Color.White,
  onPrimary = Color.White,
  onSecondary = Color.Black,
  onBackground = Color.Black,
  onSurface = Color.Black,
  */
)

@Composable
fun AppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}