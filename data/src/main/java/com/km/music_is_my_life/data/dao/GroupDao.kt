package com.km.music_is_my_life.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.km.music_is_my_life.data.entity.GroupEntity

@Dao
internal interface GroupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGroup(group: GroupEntity): Long

    @Query("select * from group_info")
    fun getGroups(): List<GroupEntity>

    @Query("select * from group_info where name=:name")
    fun getGroup(name: String): GroupEntity?
}
