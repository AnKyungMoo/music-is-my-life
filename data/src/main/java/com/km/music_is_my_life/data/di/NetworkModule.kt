package com.km.music_is_my_life.data.di

import com.km.music_is_my_life.data.service.KaraokeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {
    @Provides
    @Singleton
    fun provideChinChinService(retrofit: Retrofit): KaraokeService =
        retrofit.create(KaraokeService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.manana.kr/karaoke/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .build()
}
