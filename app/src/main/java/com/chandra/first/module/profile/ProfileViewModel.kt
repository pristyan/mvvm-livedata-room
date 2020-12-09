package com.chandra.first.module.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chandra.first.database.entity.User
import com.chandra.first.preference.DataPreference
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val preference: DataPreference by lazy { DataPreference(application) }

    private val _user: MutableLiveData<User> = MutableLiveData()
    val user: LiveData<User>
        get() = _user

    private val _isLoggedOut: MutableLiveData<Boolean> = MutableLiveData()
    val isLoggedOut: LiveData<Boolean>
        get() = _isLoggedOut

    init {
        loadProfile()
    }

    private fun loadProfile() = viewModelScope.launch {
        preference.getUser()?.let { _user.value = it }
    }

    fun logout() = viewModelScope.launch {
        preference.deleteUser()
        _isLoggedOut.value = true
    }

}