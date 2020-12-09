package com.chandra.first.module.place

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chandra.first.database.entity.Photo
import com.chandra.first.repository.local.PlaceRepository
import kotlinx.coroutines.launch

class PlaceViewModel(application: Application) : AndroidViewModel(application) {

    private val placeRepository: PlaceRepository by lazy { PlaceRepository(application) }

    private val _photos: MutableLiveData<List<Photo>> = MutableLiveData()
    val photos: LiveData<List<Photo>>
        get() = _photos

    fun fetchPhotosById(placeId: Int) = viewModelScope.launch {
        val dbRes = placeRepository.getPhotos(placeId)
        if (dbRes.isSuccess) _photos.value = dbRes.result ?: emptyList()
    }

}