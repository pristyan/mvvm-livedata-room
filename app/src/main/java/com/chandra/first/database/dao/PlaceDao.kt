package com.chandra.first.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chandra.first.database.entity.Place

@Dao
interface PlaceDao {

    @Insert
    suspend fun insert(place: Place?)

    @Query("SELECT COUNT(id) from place")
    suspend fun getPlaceCount(): Int

    @Query("SELECT * FROM place")
    suspend fun getPlaces(): List<Place>

}