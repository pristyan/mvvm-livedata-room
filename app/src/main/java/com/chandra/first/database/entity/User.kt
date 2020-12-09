package com.chandra.first.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var email: String,
    var name: String,
    var phone: String,
    var photo: String,
    var password: String

) : Serializable