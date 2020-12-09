package com.chandra.first.repository.local

import android.content.Context
import com.chandra.first.database.data.DBResult
import com.chandra.first.database.dao.UserDao
import com.chandra.first.database.entity.User

class UserRepository(context: Context): BaseLocalRepository(context) {

    private val userDao: UserDao? = db?.userDao()

    suspend fun isUserExist(): Boolean {
        return (userDao?.getUserCount() ?: 0) > 0
    }

    suspend fun insertUsers(users: List<User>) {
        users.forEach { userDao?.insert(it) }
    }

    suspend fun login(email: String, password: String): DBResult<User> {
        val result = userDao?.getUserByEmailAndPassword(email, password) ?: emptyList()
        return if (result.isEmpty()) DBResult(errorMessage = "Invalid email or password")
        else DBResult(result = result[0])
    }

}