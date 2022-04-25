package com.usman.mvvmsample.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.usman.mvvmsample.features.main.model.DogBreeds
import com.usman.mvvmsample.features.main.model.DogBreedsDao

@Database(entities = [DogBreeds::class], version = 1, exportSchema = false)
@TypeConverters(RoomConvertors::class)
abstract class TestAppDatabase: RoomDatabase() {
    abstract fun dogBreedsDAO(): DogBreedsDao
}