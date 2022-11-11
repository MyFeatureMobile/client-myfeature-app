package com.myfeature.mobile.ui.home.profile.header

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.myfeature.mobile.core.theme.textMinor

@Composable
fun ProfileNumberItem(number: Number = 0, paramName: String) {
  Column(
    modifier = Modifier
      .wrapContentSize()
  ) {
    Text(text = number.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold)
    Text(text = paramName, fontSize = 6.sp, fontWeight = FontWeight.Normal, color = MaterialTheme.colors.textMinor)
  }
}