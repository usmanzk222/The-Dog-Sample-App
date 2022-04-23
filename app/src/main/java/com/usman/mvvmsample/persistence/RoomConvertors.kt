package com.usman.mvvmsample.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class RoomConvertors {

        @TypeConverter
        inline fun <reified T> fromString(value: String?): ArrayList<T> {
            val listType: Type = object : TypeToken<List<T>?>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun <T> fromArrayList(list: List<T>?): String {
            val gson = Gson()
            return gson.toJson(list)
        }

}