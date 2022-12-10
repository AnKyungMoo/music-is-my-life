package com.km.music_is_my_life.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.km.music_is_my_life.data.dao.FavoriteMusicDao
import com.km.music_is_my_life.data.entity.FavoriteMusicEntity

@Database(entities = [FavoriteMusicEntity::class], version = 1)
internal abstract class MainDatabase : RoomDatabase() {
    abstract fun favoriteMusicDao(): FavoriteMusicDao

    companion object {
        const val MAIN_DATABASE_NAME = "main_db"
    }
}
