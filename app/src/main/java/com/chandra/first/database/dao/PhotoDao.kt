package com.chandra.first.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chandra.first.database.entity.Photo

@Dao
interface PhotoDao {

    @Insert
    suspend fun insert(photo: Photo?)

    @Query("SELECT COUNT(id) from photo")
    suspend fun getPhotoCount(): Int

    @Query("SELECT * FROM photo WHERE place_id == :id")
    suspend fun getPhotoByPlaceId(id: Int): List<Photo>

}