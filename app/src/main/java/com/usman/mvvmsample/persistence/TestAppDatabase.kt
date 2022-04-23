package com.usman.mvvmsample.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.usman.mvvmsample.features.model.Product
import com.usman.mvvmsample.features.model.ProductsDAO

/**
 * Created by Muhammad Usman on 2/15/2019.
 */

@Database(entities = [Product::class], version = 1, exportSchema = false)
@TypeConverters(RoomConvertors::class)
abstract class TestAppDatabase: RoomDatabase() {
    abstract fun productsDAO(): ProductsDAO
}