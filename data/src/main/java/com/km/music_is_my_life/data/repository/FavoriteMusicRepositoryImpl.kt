package com.km.music_is_my_life.data.repository

import com.km.music_is_my_life.data.dao.FavoriteMusicDao
import com.km.music_is_my_life.data.database.MainDatabase
import com.km.music_is_my_life.data.entity.toEntity
import com.km.music_is_my_life.domain.model.Music
import com.km.music_is_my_life.domain.repository.FavoriteMusicRepository
import javax.inject.Inject

internal class FavoriteMusicRepositoryImpl @Inject constructor(
    database: MainDatabase,
) : FavoriteMusicRepository {
    private val favoriteMusicDao: FavoriteMusicDao = database.favoriteMusicDao()

    override suspend fun insertMusic(music: Music) {
        favoriteMusicDao.insertMusic(music.toEntity())
    }
}
