package com.km.music_is_my_life.domain.repository

import com.km.music_is_my_life.domain.model.Music

interface MusicRepository {
    suspend fun getRecentMusicsOfBrand(brand: String): List<Music>
    suspend fun getMusicsByTitle(title: String, brand: String): List<Music>
}
