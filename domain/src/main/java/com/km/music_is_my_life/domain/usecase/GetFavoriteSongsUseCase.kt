package com.km.music_is_my_life.domain.usecase

import com.km.music_is_my_life.domain.model.Music
import com.km.music_is_my_life.domain.repository.FavoriteMusicRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteSongsUseCase @Inject constructor(
    private val favoriteMusicRepository: FavoriteMusicRepository,
) {
    operator fun invoke(): Flow<List<Music>> {
        return favoriteMusicRepository.getSongs()
    }
}
