package com.km.music_is_my_life.presenter.ext

import android.content.Context
import kotlin.math.roundToInt

fun Int.dp(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).roundToInt()
}
