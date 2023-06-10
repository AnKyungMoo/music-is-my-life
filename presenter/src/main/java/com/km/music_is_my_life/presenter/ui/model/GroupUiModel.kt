package com.km.music_is_my_life.presenter.ui.model

import com.km.music_is_my_life.domain.model.Group

data class GroupUiModel(
    val groupName: String,
    val color: String,
)

/* TODO: color를 enum으로 정의해둘까? 색상 정보를 여기서 알 수 있게 하면 좋을듯!? */
fun Group.toUiModel(): GroupUiModel {
    return GroupUiModel(
        groupName = name,
        color = color,
    )
}
