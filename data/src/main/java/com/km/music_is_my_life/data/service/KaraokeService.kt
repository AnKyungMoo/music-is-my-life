package com.km.music_is_my_life.data.service

import com.km.music_is_my_life.data.dto.response.RecentMusicResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

internal interface KaraokeService {
    @GET("{brand}.json")
    suspend fun getRecentMusicsOfBrand(@Path(value = "brand") brand: String): List<RecentMusicResponseBody>
}
