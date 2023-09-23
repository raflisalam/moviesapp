package com.raflisalam.moviesapp.core.common.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<Int> {
        if (value == null) {
            return emptyList()
        }
        val listType = object : TypeToken<List<Int>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(list: List<Int>?): String {
        return Gson().toJson(list)
    }
}