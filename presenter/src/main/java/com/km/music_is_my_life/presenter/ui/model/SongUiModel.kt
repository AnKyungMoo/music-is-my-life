package com.km.music_is_my_life.presenter.ui.model

import com.km.music_is_my_life.domain.model.Music

data class SongUiModel(
    val number: String,
    val title: String,
    val singer: String,
    val key: Int,
    val gender: SongGender,
) {
    companion object {
        const val DEFAULT_KEY = 0
    }
}

fun Music.toUiModel(
    key: Int = SongUiModel.DEFAULT_KEY,
    gender: SongGender,
): SongUiModel {
    return SongUiModel(
        number = this.no,
        title = this.title,
        singer = this.singer,
        key = key,
        gender = gender,
    )
}
