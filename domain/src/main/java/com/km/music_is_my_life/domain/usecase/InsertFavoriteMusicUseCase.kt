package com.km.music_is_my_life.domain.usecase

import com.km.music_is_my_life.domain.model.Music
import com.km.music_is_my_life.domain.repository.FavoriteMusicRepository
import javax.inject.Inject

class InsertFavoriteMusicUseCase @Inject constructor(
    private val repository: FavoriteMusicRepository,
) {
    suspend operator fun invoke(music: Music) {
        repository.insertMusic(music)
    }
}
