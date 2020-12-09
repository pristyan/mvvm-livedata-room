package com.chandra.first.repository.local

import android.content.Context
import com.chandra.first.database.AppDatabase

abstract class BaseLocalRepository(context: Context) {

    val db: AppDatabase? = AppDatabase.getDatabase(context)

}