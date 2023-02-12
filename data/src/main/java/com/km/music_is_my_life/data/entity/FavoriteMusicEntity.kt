package com.km.music_is_my_life.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.km.music_is_my_life.domain.model.Music

@Entity(tableName = "favorite_music")
data class FavoriteMusicEntity (
    @PrimaryKey
    val musicNumber: String,
    val title: String,
    val singer: String,
    val gender: String,
    val key: Int,
)

fun Music.toEntity(): FavoriteMusicEntity {
    return FavoriteMusicEntity(
        musicNumber = no,
        title = title,
        singer = singer,
        gender = brand,
        key = key,
    )
}
