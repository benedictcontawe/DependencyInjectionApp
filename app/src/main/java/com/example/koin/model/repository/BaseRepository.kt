package com.example.koin.model.repository

import androidx.lifecycle.LiveData
import com.example.koin.model.room.CustomEntity

interface BaseRepository {

    fun  insert(customEntity: CustomEntity)

    fun  update(customEntity: CustomEntity)

    fun  delete(customEntity: CustomEntity)

    fun  deleteAll()

    fun getAll() : LiveData<List<CustomEntity>>
}