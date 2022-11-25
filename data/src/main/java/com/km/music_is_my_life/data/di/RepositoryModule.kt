package com.km.music_is_my_life.data.di

import com.km.music_is_my_life.data.repository.MusicRepositoryImpl
import com.km.music_is_my_life.domain.repository.MusicRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindMusicRepository(musicRepositoryImpl: MusicRepositoryImpl): MusicRepository
}
