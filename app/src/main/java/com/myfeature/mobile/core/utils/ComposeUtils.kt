package com.myfeature.mobile.core.utils

import android.text.Spanned
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

val modifierMaxWidth = Modifier.fillMaxWidth()

@Composable
@ReadOnlyComposable
fun fontDimensionResource(@DimenRes id: Int) = dimensionResource(id = id).value.sp

@Composable
fun annotatedStringResource(@StringRes id: Int, vararg formatArgs: Any): AnnotatedString {
  val resources = LocalContext.current.resources
  return remember(id) {
    val text = resources.getText(id)
    spannableStringToAnnotatedString(text)
  }
}

@Composable
fun annotatedStringResource(@StringRes id: Int): AnnotatedString {
  val resources = LocalContext.current.resources
  return remember(id) {
    val text = resources.getText(id)
    spannableStringToAnnotatedString(text)
  }
}

private fun spannableStringToAnnotatedString(text: CharSequence): AnnotatedString {
  return if (text is Spanned) {
    val spanStyles = mutableListOf<AnnotatedString.Range<SpanStyle>>()
    spanStyles.addAll(text.getSpans(0, text.length, UnderlineSpan::class.java).map {
      AnnotatedString.Range(
        SpanStyle(textDecoration = TextDecoration.Underline),
        text.getSpanStart(it),
        text.getSpanEnd(it)
      )
    })
    spanStyles.addAll(text.getSpans(0, text.length, StyleSpan::class.java).map {
      AnnotatedString.Range(
        SpanStyle(fontWeight = FontWeight.Bold),
        text.getSpanStart(it),
        text.getSpanEnd(it)
      )
    })
    AnnotatedString(text.toString(), spanStyles = spanStyles)
  } else {
    AnnotatedString(text.toString())
  }
}