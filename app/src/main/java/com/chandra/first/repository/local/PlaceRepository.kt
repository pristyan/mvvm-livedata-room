package com.chandra.first.repository.local

import android.content.Context
import com.chandra.first.database.data.DBResult
import com.chandra.first.database.dao.PhotoDao
import com.chandra.first.database.dao.PlaceDao
import com.chandra.first.database.entity.Photo
import com.chandra.first.database.entity.Place

class PlaceRepository(context: Context) : BaseLocalRepository(context) {

    private val placeDao: PlaceDao? = db?.placeDao()
    private val photoDao: PhotoDao? = db?.photoDao()

    suspend fun isPlaceExist(): Boolean {
        return (placeDao?.getPlaceCount() ?: 0) > 0
    }

    suspend fun insertPlaces(places: List<Place>) {
        places.forEach { placeDao?.insert(it) }
    }

    suspend fun getPlaces(): DBResult<List<Place>> {
        val result = placeDao?.getPlaces() ?: emptyList()
        return if (result.isEmpty()) DBResult(errorMessage = "No place found")
        else DBResult(result = result)
    }

    suspend fun isPhotoExist(): Boolean {
        return (photoDao?.getPhotoCount() ?: 0) > 0
    }

    suspend fun insertPhotos(photos: List<Photo>) {
        photos.forEach { photoDao?.insert(it) }
    }

    suspend fun getPhotos(placeId: Int): DBResult<List<Photo>> {
        val result = photoDao?.getPhotoByPlaceId(placeId) ?: emptyList()
        return if (result.isEmpty()) DBResult(errorMessage = "No photo found")
        else DBResult(result = result)
    }

}