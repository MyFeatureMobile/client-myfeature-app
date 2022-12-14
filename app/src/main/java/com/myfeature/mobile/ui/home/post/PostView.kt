package com.myfeature.mobile.ui.home.post

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myfeature.mobile.core.theme.barBackground
import com.myfeature.mobile.core.theme.controlMain
import com.myfeature.mobile.core.theme.moncerratFontFamily
import com.myfeature.mobile.core.theme.textMinor
import com.myfeature.mobile.ui.home.common.HomePostsHeader
import com.myfeature.mobile.ui.home.post.PostState.Loading
import com.myfeature.mobile.ui.home.post.PostState.Post
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostView(
  modifier: Modifier = Modifier,
  postId: Long = 1
) {

  val viewModel: PostViewModel = koinViewModel()
  val context = LocalContext.current
  val postState = viewModel.listenToPostModel(postId).collectAsState(initial = Loading)
  when (val value = postState.value) {
    is Post -> {
      val post = value.post
      Column(modifier = modifier) {
        HomePostsHeader(
          modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
          showAddPostButton = false
        )
        LazyColumn(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
          item {
            PostNamePhotosView(
              modifier = Modifier
                .fillMaxWidth(),
              postState = post
            )
          }
          item {
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(color = MaterialTheme.colors.barBackground, shape = RoundedCornerShape(15.dp))
            ) {
              Text(
                text = post.description,
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
              )
            }
          }
          item {
            Box(
              modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .background(color = MaterialTheme.colors.barBackground, shape = RoundedCornerShape(15.dp))
            ) {
              Column(
                modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 12.dp, vertical = 4.dp)
              ) {
                Text(
                  text = "Code",
                  fontSize = 16.sp,
                  fontWeight = Companion.Bold,
                  color = MaterialTheme.colors.controlMain
                )
                ClickableText(
                  text = buildLink(post.codeLink, MaterialTheme.colors.textMinor),
                  modifier = Modifier.padding(top = 4.dp),
                  onClick = {
                    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(post.codeLink)))
                  })
              }
            }
          }
        }
      }
    }
    Loading, PostState.Error -> {
      CircularProgressIndicator()
    }
  }
}

private fun buildLink(text: String, color: Color): AnnotatedString = buildAnnotatedString {
  append(text)
  addStyle(
    SpanStyle(
      color = color,
      fontSize = 10.sp,
      fontWeight = Companion.Medium,
      textDecoration = TextDecoration.Underline,
      fontFamily = moncerratFontFamily
    ),
    start = 0,
    end = text.length
  )
  addStringAnnotation(
    tag = "URL",
    annotation = text,
    start = 0,
    end = text.length
  )
}

@Preview
@Composable
fun PostPreview() {
  PostView()
}