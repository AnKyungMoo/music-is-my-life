package com.km.music_is_my_life.domain.usecase

import com.km.music_is_my_life.domain.model.Music
import com.km.music_is_my_life.domain.repository.MusicRepository
import javax.inject.Inject

class GetRecentMusicsUseCase @Inject constructor(
    private val repository: MusicRepository
) {
    suspend operator fun invoke(brand: String): List<Music> {
        return repository.getRecentMusicsOfBrand(brand)
    }
}
