package com.chandra.first.data

import com.chandra.first.database.entity.Place
import java.io.Serializable

data class PlaceResponse(
    val data: List<Place>
): Serializable