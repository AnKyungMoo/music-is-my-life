package com.km.music_is_my_life.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_music")
data class FavoriteMusicEntity (
    @PrimaryKey
    val musicNumber: Int,
    val title: String,
    val singer: String,
    val gender: String,
    val key: Int,
)
