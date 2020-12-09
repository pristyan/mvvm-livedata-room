package com.chandra.first.module.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chandra.first.preference.DataPreference
import com.chandra.first.repository.local.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository: UserRepository by lazy {
        UserRepository(application)
    }

    private val preference: DataPreference by lazy {
        DataPreference(application)
    }

    private val _error: MutableLiveData<String?> = MutableLiveData()
    val errorMessage: LiveData<String?>
        get() = _error

    private val _loginSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    fun login(email: String, password: String) = viewModelScope.launch {
        if (email.isBlank() || password.isBlank()) {
            _error.value = "Email and password can not be empty"
            return@launch
        }

        val dbRes = userRepository.login(email, password)
        if (dbRes.isSuccess) dbRes.result?.let { preference.setUser(it) }

        if (dbRes.isSuccess) _loginSuccess.value = true
        else _error.value = dbRes.errorMessage
    }

}