package com.km.music_is_my_life.presenter.ui.model

import com.km.music_is_my_life.domain.model.Music
import com.km.music_is_my_life.domain.model.SongGender

data class SongUiModel(
    val number: String,
    val title: String,
    val singer: String,
    val key: Int,
    val gender: SongGender,
    val groupName: String,
)

fun Music.toUiModel(): SongUiModel {
    return SongUiModel(
        number = this.no,
        title = this.title,
        singer = this.singer,
        key = key,
        gender = gender,
        groupName = this.groupName,
    )
}
