package com.chandra.first.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "photo")
data class Photo(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @SerializedName("place_id")
    @ColumnInfo(name = "place_id")
    var placeId: Int,

    var url: String

) : Serializable