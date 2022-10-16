package com.myfeature.mobile.core.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.myfeature.mobile.R

val ralewayFontFamily = FontFamily(
  Font(R.font.raleway_medium, weight = FontWeight.Normal),
  Font(R.font.raleway_bold, weight = FontWeight.Bold)
)

val Typography = Typography(
  defaultFontFamily = ralewayFontFamily,
  body1 = TextStyle(
    fontFamily = ralewayFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
  ),
  button = TextStyle(
    fontFamily = ralewayFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 16.sp
  )
/*
  caption = TextStyle(
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.Normal,
      fontSize = 12.sp
  )
  */
)