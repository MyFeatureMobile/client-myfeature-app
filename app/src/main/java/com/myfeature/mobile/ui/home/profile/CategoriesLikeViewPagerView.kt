package com.myfeature.mobile.ui.home.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myfeature.mobile.core.theme.controlMain
import com.myfeature.mobile.core.theme.textMain
import com.myfeature.mobile.core.utils.Functions
import com.myfeature.mobile.ui.home.profile.model.Category
import com.myfeature.mobile.ui.home.profile.model.Category.POSTS

@Composable
fun CategoriesLikeViewPagerView(
  selected: MutableState<Category>,
) {
  val modifier = Modifier.fillMaxWidth()
  Column(modifier = modifier.wrapContentHeight()) {
    Row(modifier = modifier.wrapContentHeight(), horizontalArrangement = Arrangement.SpaceAround) {
      Category.values().forEach {
        TextButton(
          onClick = {
            selected.value = it
          }
        ) {
          val textColor = if (selected.value == it) {
            MaterialTheme.colors.controlMain
          } else {
            MaterialTheme.colors.textMain
          }
          Text(text = stringResource(it.title), color = textColor, fontSize = 12.sp)
        }
      }
    }
    Divider(color = MaterialTheme.colors.textMain, thickness = 1.dp, modifier = Modifier.padding(horizontal = 16.dp))
  }
}

@Preview
@Composable
fun CategoriesView() {
  CategoriesLikeViewPagerView(selected = remember { mutableStateOf(POSTS) })
}