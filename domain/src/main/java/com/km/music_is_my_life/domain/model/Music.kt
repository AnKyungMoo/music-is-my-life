package com.km.music_is_my_life.domain.model

data class Music(
    val no: String,
    val title: String,
    val singer: String,
    val gender: SongGender = SongGender.MAN,
    val key: Int = DEFAULT_KEY,
    val groupName: String,
) {
    companion object {
        const val DEFAULT_KEY = 0
    }
}
