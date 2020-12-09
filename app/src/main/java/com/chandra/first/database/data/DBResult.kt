package com.chandra.first.database.data

import java.io.Serializable

class DBResult<T>(
    val result: T? = null,
    val errorMessage: String? = null
) : Serializable {

    val isSuccess: Boolean
        get() = result != null

}