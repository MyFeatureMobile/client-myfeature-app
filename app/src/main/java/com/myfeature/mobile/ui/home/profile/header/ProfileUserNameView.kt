package com.myfeature.mobile.ui.home.profile.header

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myfeature.mobile.core.theme.TextColor
import com.myfeature.mobile.core.utils.Functions

@Composable
fun ProfileUserNameView(
  modifier: Modifier = Modifier,
  name: String = "Simon Kulyomin",
  onEditName: () -> Unit = Functions::empty
) {
  Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(text = name, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextColor)
    Icon(
      imageVector = Icons.Outlined.Edit,
      modifier = Modifier
        .padding(start = 8.dp)
        .size(18.dp)
        .clickable(
          interactionSource = remember { MutableInteractionSource() },
          indication = rememberRipple(bounded = false, radius = 12.dp),
          onClick = onEditName
        ),
      contentDescription = "Edit your name"
    )
  }
}

@Preview
@Composable
fun PreviewName() {
  ProfileUserNameView()
}