package com.km.music_is_my_life.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.km.music_is_my_life.data.entity.FavoriteMusicEntity

@Dao
interface FavoriteMusicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMusic(favoriteMusicEntity: FavoriteMusicEntity): Long

    @Query("select * from favorite_music")
    fun getFavoriteMusics(): List<FavoriteMusicEntity>
}
