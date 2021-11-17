package com.yiqqi.beers.data.source.local.impl.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun restoreStringList(listOfString: String?): List<String?>? {
        return Gson().fromJson(
            listOfString,
            object : TypeToken<List<String?>?>() {}.type
        )
    }

    @TypeConverter
    fun saveStringList(listOfString: List<String?>?): String? {
        return Gson().toJson(listOfString)
    }

}