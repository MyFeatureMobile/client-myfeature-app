package com.myfeature.mobile.ui

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.myfeature.mobile.R.drawable

@Composable
fun Logo(modifier: Modifier = Modifier) {
  Image(
    painter = painterResource(id = drawable.ic_logo),
    contentDescription = "",
    contentScale = ContentScale.Crop,
    modifier = modifier
  )
}