package com.usman.mvvmsample.persistence

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


/**
 * Created by Muhammad Usman on 3/8/2019.
 */
interface BaseDAO <EN> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(entity: List<EN>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg entity: EN)

    @Delete
    suspend fun delete(vararg entity: EN)

    @Update
    suspend fun update(vararg entity: EN)

    fun getAll(): LiveData<List<EN>>
}