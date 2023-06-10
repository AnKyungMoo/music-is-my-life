package com.km.music_is_my_life.data.dto.response

import com.google.gson.annotations.SerializedName
import com.km.music_is_my_life.domain.model.Group
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
            no = no,
            title = title,
            singer = singer,
            // api 검색을 통해서 얻어온 response는 그룹 정보를 가지지 않는다.
            groupName = Group.DEFAULT_GROUP_NAME,
        )
    }
}
