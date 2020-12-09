package com.chandra.first.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "place")
data class Place(

    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String,
    var thumbnail: String,

) : Serializable