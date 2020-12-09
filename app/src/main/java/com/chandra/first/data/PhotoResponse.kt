package com.chandra.first.data

import com.chandra.first.database.entity.Photo
import java.io.Serializable

data class PhotoResponse(
    val data: List<Photo>
): Serializable