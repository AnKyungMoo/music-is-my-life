package com.km.music_is_my_life.domain.usecase

import com.km.music_is_my_life.domain.model.Group
import com.km.music_is_my_life.domain.repository.GroupRepository
import javax.inject.Inject

class AddGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(group: Group) {
        /* TODO: 이름이 "기본그룹" 인 경우에는 등록 실패 */
        groupRepository.addGroup(group)
    }
}
