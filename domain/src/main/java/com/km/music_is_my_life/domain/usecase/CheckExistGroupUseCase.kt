package com.km.music_is_my_life.domain.usecase

import com.km.music_is_my_life.domain.repository.GroupRepository
import javax.inject.Inject

class CheckExistGroupUseCase @Inject constructor(
    private val groupRepository: GroupRepository
) {
    suspend operator fun invoke(groupName: String): Boolean {
        return groupRepository.getGroup(groupName) != null
    }
}
