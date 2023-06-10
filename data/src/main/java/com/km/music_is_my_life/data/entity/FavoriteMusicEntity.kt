package com.km.music_is_my_life.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.km.music_is_my_life.domain.model.Music

@Entity(
    tableName = "favorite_music",
    foreignKeys = [
        ForeignKey(
            entity = GroupEntity::class,
            parentColumns = ["name"],
            childColumns = ["group_name"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
internal data class FavoriteMusicEntity (
    @PrimaryKey @ColumnInfo(name = "music_number") val musicNumber: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "singer") val singer: String,
    @ColumnInfo(name = "gender") val gender: String,
    @ColumnInfo(name = "key") val key: Int,
    @ColumnInfo(name = "group_name") val groupName: String?,
)

internal fun Music.toEntity(): FavoriteMusicEntity {
    return FavoriteMusicEntity(
        musicNumber = no,
        title = title,
        singer = singer,
        gender = gender.value,
        key = key,
        groupName = groupName,
    )
}
