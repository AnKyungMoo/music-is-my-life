package com.km.music_is_my_life.domain.repository

import com.km.music_is_my_life.domain.model.Music
import kotlinx.coroutines.flow.Flow

interface FavoriteMusicRepository {
    suspend fun insertMusic(music: Music)

    fun getSongs(): Flow<List<Music>>
}
