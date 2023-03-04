package com.km.music_is_my_life.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "group_info")
internal data class GroupEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val color: String,
)
