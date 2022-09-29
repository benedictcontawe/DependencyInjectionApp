package com.example.koin.repository

import androidx.lifecycle.LiveData
import com.example.koin.room.CustomEntity

public interface BaseRepository {

    public fun giveRepository() : String

    suspend fun insert(customEntity : CustomEntity)

    suspend fun update(customEntity : CustomEntity)

    suspend fun delete(customEntity : CustomEntity)

    suspend fun deleteAll()

    public fun getAll() : LiveData<List<CustomEntity>>
}