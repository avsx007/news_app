package com.android.testapp.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Collections.emptyList


object TypeConverter {
    @TypeConverter
    @JvmStatic
    fun GsonToImageList(data: String): List<String> {
        if (data == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<String>>() {
        }.type

        return Gson().fromJson(data, listType) as List<String>
    }

    @TypeConverter
    @JvmStatic
    fun ImageListToString(someObjects: List<String>): String {
        return Gson().toJson(someObjects)
    }
}