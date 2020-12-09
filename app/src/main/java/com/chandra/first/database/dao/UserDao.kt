package com.chandra.first.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.chandra.first.database.entity.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User?)

    @Query("SELECT COUNT(id) from user")
    suspend fun getUserCount(): Int

    @Query("SELECT * from user WHERE email == :email AND password == :password")
    suspend fun getUserByEmailAndPassword(email: String, password: String): List<User>?

}