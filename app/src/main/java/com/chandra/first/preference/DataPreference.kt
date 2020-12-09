package com.chandra.first.preference

import android.content.Context
import android.content.SharedPreferences
import com.chandra.first.database.entity.User
import com.google.gson.Gson

class DataPreference(context: Context) {

    companion object {
        private const val PREFS_NAME = "test_app_preference"
    }

    private val prefs: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun setUser(user: User) {
        with(prefs.edit()) {
            putString("user", Gson().toJson(user))
            apply()
        }
    }

    fun getUser(): User? {
        val temp = prefs.getString("user", null)
        return if (temp == null) temp
        else Gson().fromJson(temp, User::class.java)
    }

    fun isLoggedIn(): Boolean {
        return getUser() != null
    }

    fun deleteUser() {
        with(prefs.edit()) {
            remove("user")
            apply()
        }
    }
}