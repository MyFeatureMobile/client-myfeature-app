package com.myfeature.mobile.core.utils

import androidx.annotation.ColorInt
import androidx.annotation.Size

@ColorInt
fun parseColor(@Size(min = 1) colorString: String): Int {
    return android.graphics.Color.parseColor(colorString)
}