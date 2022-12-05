package com.km.music_is_my_life.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TempEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
)
