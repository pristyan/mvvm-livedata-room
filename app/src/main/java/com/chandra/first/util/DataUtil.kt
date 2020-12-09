package com.chandra.first.util

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

fun Context.getJsonFromAsset(path: String): JSONObject? {

    val json: JSONObject?
    val charset: Charset = Charsets.UTF_8
    try {
        val iStream = assets.open(path)
        val size = iStream.available()
        val buffer = ByteArray(size)
        iStream.read(buffer)
        iStream.close()
        json = JSONObject(String(buffer, charset))
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }

    return json
}
