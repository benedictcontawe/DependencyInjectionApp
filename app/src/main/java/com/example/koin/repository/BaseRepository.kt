package com.example.koin.repository

import androidx.lifecycle.LiveData
import com.example.koin.room.CustomEntity

interface BaseRepository {

    fun  insert(customEntity: CustomEntity)

    fun  update(customEntity: CustomEntity)

    fun  delete(customEntity: CustomEntity)

    fun  deleteAll()

    fun getAll() : LiveData<List<CustomEntity>>
}