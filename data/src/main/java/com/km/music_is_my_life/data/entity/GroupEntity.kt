package com.km.music_is_my_life.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.km.music_is_my_life.domain.model.Group

@Entity(tableName = "group_info")
internal data class GroupEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "color") val color: String,
)

internal fun GroupEntity.toDomainModel(): Group =
    Group(
        name = name,
        color = color,
    )

internal fun Group.toEntity(): GroupEntity {
    return GroupEntity(
        name = name,
        color = color,
    )
}
