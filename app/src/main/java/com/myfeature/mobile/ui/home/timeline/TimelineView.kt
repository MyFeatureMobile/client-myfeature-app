package com.myfeature.mobile.ui.home.timeline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TimelineView(modifier: Modifier = Modifier) {
  Box(modifier = modifier.fillMaxSize()) {
    Text("Timeline")
  }
}