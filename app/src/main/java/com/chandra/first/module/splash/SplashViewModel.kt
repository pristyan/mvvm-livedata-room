package com.chandra.first.module.splash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chandra.first.data.PhotoResponse
import com.chandra.first.data.PlaceResponse
import com.chandra.first.data.UserResponse
import com.chandra.first.preference.DataPreference
import com.chandra.first.repository.local.PlaceRepository
import com.chandra.first.repository.local.UserRepository
import com.chandra.first.util.getJsonFromAsset
import com.google.gson.Gson
import kotlinx.coroutines.launch

class SplashViewModel(application: Application) : AndroidViewModel(application) {

    private val placeRepository: PlaceRepository by lazy { PlaceRepository(application) }
    private val userRepository: UserRepository by lazy { UserRepository(application) }
    private val preference: DataPreference by lazy { DataPreference(application) }

    private val userResponse: UserResponse by lazy {
        Gson().fromJson(application.getJsonFromAsset(
            "users_response.json").toString(),
            UserResponse::class.java
        )
    }

    private val placeResponse: PlaceResponse by lazy {
        Gson().fromJson(application.getJsonFromAsset(
            "places_response.json").toString(),
            PlaceResponse::class.java
        )
    }

    private val photoResponse: PhotoResponse by lazy {
        Gson().fromJson(application.getJsonFromAsset(
            "photos_response.json").toString(),
            PhotoResponse::class.java
        )
    }

    private val _goToNextPage: MutableLiveData<String> = MutableLiveData()
    val goToNextPage: LiveData<String>
        get() = _goToNextPage

    init {
        check()
    }

    private fun check() {
        viewModelScope.launch {

            val isPlaceReady = placeRepository.isPlaceExist()
            if (!isPlaceReady) placeRepository.insertPlaces(placeResponse.data)

            val isPhotoReady = placeRepository.isPhotoExist()
            if (!isPhotoReady) placeRepository.insertPhotos(photoResponse.data)

            val isUserReady = userRepository.isUserExist()
            if (!isUserReady) userRepository.insertUsers(userResponse.data)

            _goToNextPage.value = if (preference.isLoggedIn()) "main" else "login"

        }
    }

}