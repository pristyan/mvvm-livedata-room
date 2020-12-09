package com.chandra.first.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chandra.first.database.dao.PhotoDao
import com.chandra.first.database.dao.PlaceDao
import com.chandra.first.database.dao.UserDao
import com.chandra.first.database.entity.Photo
import com.chandra.first.database.entity.Place
import com.chandra.first.database.entity.User

@Database(entities = [User::class, Place::class, Photo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun placeDao(): PlaceDao
    abstract fun photoDao(): PhotoDao

    companion object {

        private const val DB_NAME = "test_app_db"
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                    .build()
            }

            return instance
        }
    }

}