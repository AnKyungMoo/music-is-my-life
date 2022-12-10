package com.km.music_is_my_life.domain.usecase

import com.km.music_is_my_life.domain.model.Music
import com.km.music_is_my_life.domain.repository.MusicRepository
import javax.inject.Inject

class SearchForMusicUseCase @Inject constructor(
    private val musicRepository: MusicRepository,
) {
    suspend operator fun invoke(searchWord: String, brand: String): List<Music> {
        val result = mutableListOf<Music>()
        result.addAll(musicRepository.getMusicsByTitle(title = searchWord, brand = brand))
        result.addAll(musicRepository.getMusicsBySinger(singer = searchWord, brand = brand))

        return result.toList()
    }
}
