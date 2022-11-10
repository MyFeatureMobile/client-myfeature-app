package com.myfeature.mobile.ui.home.profile

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProfileView(modifier: Modifier = Modifier) {
  LazyColumn {
    item {
      ProfileHeaderView(modifier = modifier.wrapContentSize(), onPhotoChangeClick = { /* TODO */ })
    }
  }
}