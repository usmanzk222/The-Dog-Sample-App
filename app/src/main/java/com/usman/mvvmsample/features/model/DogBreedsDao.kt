package com.usman.mvvmsample.features.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.usman.mvvmsample.persistence.BaseDAO
import kotlinx.coroutines.flow.Flow

@Dao
interface DogBreedsDao : BaseDAO<DogBreeds> {

    @Query("SELECT * FROM DogBreeds")
    override fun getAll(): LiveData<List<DogBreeds>>

    @Query("SELECT * FROM DogBreeds WHERE id = :id")
    fun getProduct(id: Int): Flow<DogBreeds>

    @Query("DELETE FROM DogBreeds")
    fun deleteAll()
}
