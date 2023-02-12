package com.km.music_is_my_life.domain.repository

import com.km.music_is_my_life.domain.model.Music

interface FavoriteMusicRepository {
    suspend fun insertMusic(music: Music)
}
