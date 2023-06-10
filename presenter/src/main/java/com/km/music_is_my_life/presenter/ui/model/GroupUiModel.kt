package com.km.music_is_my_life.presenter.ui.model

import com.km.music_is_my_life.domain.model.Group

data class GroupUiModel(
    val groupName: String,
    val color: GroupColor,
) {
    fun toDomainModel(): Group {
        return Group(
            name = groupName,
            color = color.colorName,
        )
    }

    companion object {
        val DEFAULT_GROUP = GroupUiModel(groupName = Group.DEFAULT_GROUP_NAME, color = GroupColor.PURPLE)
    }
}

fun Group.toUiModel(): GroupUiModel {
    val groupColor = GroupColor.fromColorName(color)
    requireNotNull(groupColor) {
        "정의되지 않은 색상 값이 있습니다."
    }

    return GroupUiModel(
        groupName = name,
        color = groupColor,
    )
}
