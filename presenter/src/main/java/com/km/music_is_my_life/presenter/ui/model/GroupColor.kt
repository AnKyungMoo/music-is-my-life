package com.km.music_is_my_life.presenter.ui.model

import com.km.music_is_my_life.presenter.R

enum class GroupColor(val colorName: String, val colorResId: Int) {
    PURPLE(colorName = "purple", colorResId = R.color.Fill07),
    ORANGE(colorName = "orange", colorResId = R.color.Fill08),
    GREEN(colorName = "green", colorResId = R.color.Fill09),
    SKY(colorName = "sky", colorResId = R.color.Fill10),
    MAGENTA(colorName = "magenta", colorResId = R.color.Fill11);

    companion object {
        fun fromColorName(colorName: String): GroupColor? =
            values().associateBy(GroupColor::colorName)[colorName]
    }
}
