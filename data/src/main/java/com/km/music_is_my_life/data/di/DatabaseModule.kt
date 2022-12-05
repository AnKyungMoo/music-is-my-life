package com.km.music_is_my_life.data.di

import android.content.Context
import androidx.room.Room
import com.km.music_is_my_life.data.database.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideMainDatabase(@ApplicationContext context: Context): MainDatabase =
        Room.databaseBuilder(context, MainDatabase::class.java, MainDatabase.MAIN_DATABASE_NAME)
            .build()
}
