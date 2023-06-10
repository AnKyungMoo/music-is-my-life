package com.km.music_is_my_life.data.repository

import com.km.music_is_my_life.data.dao.FavoriteMusicDao
import com.km.music_is_my_life.data.database.MainDatabase
import com.km.music_is_my_life.data.entity.toEntity
import com.km.music_is_my_life.domain.model.Music
import com.km.music_is_my_life.domain.repository.FavoriteMusicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class FavoriteMusicRepositoryImpl @Inject constructor(
    database: MainDatabase,
) : FavoriteMusicRepository {
    private val favoriteMusicDao: FavoriteMusicDao = database.favoriteMusicDao()

    override suspend fun insertMusic(music: Music) {
        withContext(Dispatchers.IO) {
            favoriteMusicDao.insertMusic(music.toEntity())
        }
    }

    override fun getSongs(): Flow<List<Music>> {
        return favoriteMusicDao.getFavoriteMusics().map {
            it.map { entity ->
                entity.toDomainModel()
            }
        }
    }
}
