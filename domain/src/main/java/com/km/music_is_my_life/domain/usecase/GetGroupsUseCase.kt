package com.km.music_is_my_life.domain.usecase

import com.km.music_is_my_life.domain.model.Group
import com.km.music_is_my_life.domain.repository.GroupRepository
import javax.inject.Inject

class GetGroupsUseCase @Inject constructor(
    private val groupRepository: GroupRepository,
) {
    suspend operator fun invoke(): List<Group> {
        return groupRepository.getGroups()
    }
}
