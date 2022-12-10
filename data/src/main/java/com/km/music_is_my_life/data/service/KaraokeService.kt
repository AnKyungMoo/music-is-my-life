package com.km.music_is_my_life.data.service

import com.km.music_is_my_life.data.dto.response.MusicInfoResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

internal interface KaraokeService {
    @GET("{brand}.json")
    suspend fun getRecentMusicsOfBrand(@Path(value = "brand") brand: String): List<MusicInfoResponseBody>

    @GET("song/{title}/{brand}.json")
    suspend fun getMusicsByTitle(
        @Path(value = "title") title: String,
        @Path(value = "brand") brand: String,
    ): List<MusicInfoResponseBody>

    @GET("singer/{singer}/{brand}.json")
    suspend fun getMusicsBySinger(
        @Path(value = "singer") singer: String,
        @Path(value = "brand") brand: String,
    ): List<MusicInfoResponseBody>
}
