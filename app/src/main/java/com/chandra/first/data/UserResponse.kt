package com.chandra.first.data

import com.chandra.first.database.entity.User
import java.io.Serializable

data class UserResponse(
    val data: List<User>
): Serializable