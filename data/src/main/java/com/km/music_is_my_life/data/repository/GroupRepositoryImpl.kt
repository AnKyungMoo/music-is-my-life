package com.km.music_is_my_life.data.repository

import com.km.music_is_my_life.data.dao.GroupDao
import com.km.music_is_my_life.data.database.MainDatabase
import com.km.music_is_my_life.data.entity.toDomainModel
import com.km.music_is_my_life.data.entity.toEntity
import com.km.music_is_my_life.domain.model.Group
import com.km.music_is_my_life.domain.repository.GroupRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class GroupRepositoryImpl @Inject constructor(
    database: MainDatabase,
) : GroupRepository {
    private val groupDao: GroupDao = database.groupDao()

    override suspend fun getGroups(): List<Group> {
        return withContext(Dispatchers.IO) {
            groupDao.getGroups().map { it.toDomainModel() }
        }
    }

    override suspend fun addGroup(group: Group) {
        withContext(Dispatchers.IO) {
            groupDao.addGroup(group.toEntity())
        }
    }
}
