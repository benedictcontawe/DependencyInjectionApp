package com.example.dagger_hilt.repository

import androidx.lifecycle.LiveData
import com.example.dagger_hilt.room.CustomEntity

interface BaseRepository {

    fun giveRepository() : String

    suspend fun  insert(customEntity : CustomEntity)

    suspend fun  update(customEntity : CustomEntity)

    suspend fun  delete(customEntity : CustomEntity)

    suspend fun  deleteAll()

    fun  getAll() : LiveData<List<CustomEntity>>

}