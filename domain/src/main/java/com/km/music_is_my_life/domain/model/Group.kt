package com.km.music_is_my_life.domain.model

data class Group(
    val name: String,
    val color: String,
) {
    companion object {
        const val DEFAULT_GROUP_NAME = "기본그룹"
    }
}
