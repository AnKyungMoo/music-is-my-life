package com.km.music_is_my_life.domain.repository

import com.km.music_is_my_life.domain.model.Group

interface GroupRepository {
    fun getGroups(): List<Group>
}