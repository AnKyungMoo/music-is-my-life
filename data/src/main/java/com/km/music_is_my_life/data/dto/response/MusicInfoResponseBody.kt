package com.km.music_is_my_life.data.dto.response

import com.google.gson.annotations.SerializedName
import com.km.music_is_my_life.domain.model.Music

internal data class MusicInfoResponseBody(
    @SerializedName("brand") val brand: String,
    @SerializedName("no") val no: String,
    @SerializedName("title") val title: String,
    @SerializedName("singer") val singer: String,
    @SerializedName("composer") val composer: String,
    @SerializedName("lyricist") val lyricist: String,
    @SerializedName("release") val release: String,
) {
    fun toDomainModel(): Music {
        return Music(
            brand = brand,
            no = no,
            title = title,
            singer = singer,
            composer = composer,
            lyricist = lyricist,
            release = release,
            groupName = null,
        )
    }
}
