package com.km.music_is_my_life.domain.model

data class Music(
    val brand: String,
    val no: String,
    val title: String,
    val singer: String,
    val composer: String,
    val lyricist: String,
    val release: String,
    val gender: SongGender = SongGender.MAN,
    val key: Int = DEFAULT_KEY
) {
    companion object {
        const val DEFAULT_KEY = 0
    }
}
