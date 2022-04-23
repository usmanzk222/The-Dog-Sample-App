package com.usman.mvvmsample.features.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.usman.mvvmsample.persistence.BaseDAO
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDAO : BaseDAO<Product> {

    @Query("SELECT * FROM products")
    override fun getAll(): LiveData<List<Product>>

    @Query("SELECT * FROM products WHERE id = :id")
    fun getProduct(id: Int): Flow<Product>

    @Query("DELETE FROM products")
    fun deleteAll()
}
