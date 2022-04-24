package com.usman.mvvmsample.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.usman.mvvmsample.features.model.Image


class RoomConvertors {

        @TypeConverter
        fun fromString(value: String?): Image{
            return Gson().fromJson(value, Image::class.java)
        }

        @TypeConverter
        fun fromObject(obj: Image?): String {
            val gson = Gson()
            return gson.toJson(obj, Image::class.java)
        }

}