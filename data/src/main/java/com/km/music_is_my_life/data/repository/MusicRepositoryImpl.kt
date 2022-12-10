package com.km.music_is_my_life.data.repository

import com.km.music_is_my_life.data.service.KaraokeService
import com.km.music_is_my_life.domain.model.Music
import com.km.music_is_my_life.domain.repository.MusicRepository
import javax.inject.Inject

internal class MusicRepositoryImpl @Inject constructor(
    private val service: KaraokeService,
) : MusicRepository {
    override suspend fun getRecentMusicsOfBrand(brand: String): List<Music> {
        return service.getRecentMusicsOfBrand(brand = brand).map {
            it.toDomainModel()
        }
    }

    override suspend fun getMusicsByTitle(title: String, brand: String): List<Music> {
        return service.getMusicsByTitle(title = title, brand = brand).map {
            it.toDomainModel()
        }
    }
}
