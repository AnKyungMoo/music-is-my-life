package com.km.music_is_my_life.domain.model

enum class SongGender(val value: String) {
    MAN(value = "man"),
    WOMAN(value = "woman");

    companion object {
        fun fromValues(value: String): SongGender? = values().associateBy(SongGender::value)[value]
    }
}
