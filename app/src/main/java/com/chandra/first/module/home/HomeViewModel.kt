package com.chandra.first.module.home

import android.app.Application
import androidx.lifecycle.*
import com.chandra.first.database.entity.Place
import com.chandra.first.repository.local.PlaceRepository
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val placeRepository: PlaceRepository by lazy { PlaceRepository(application) }

    private val _places: MutableLiveData<List<Place>> = MutableLiveData()
    val places: LiveData<List<Place>>
        get() = _places

    init {
        fetchPlaces()
    }

    private fun fetchPlaces() = viewModelScope.launch {
        val dbRes = placeRepository.getPlaces()
        if (dbRes.isSuccess) _places.value = dbRes.result ?: emptyList()
    }

}