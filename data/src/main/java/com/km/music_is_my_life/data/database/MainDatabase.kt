package com.km.music_is_my_life.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1)
abstract class MainDatabase : RoomDatabase() {

    companion object {
        const val MAIN_DATABASE_NAME = "main_db"
    }
}
