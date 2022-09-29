package com.example.dependencyinjectionapp.repository

import androidx.lifecycle.LiveData
import com.example.dependencyinjectionapp.room.CustomEntity

public interface BaseRepository {

    public fun giveRepository() : String

    suspend fun  insert(customEntity : CustomEntity)

    suspend fun  update(customEntity : CustomEntity)

    suspend fun  delete(customEntity : CustomEntity)

    suspend fun  deleteAll()

    fun  getAll() : LiveData<List<CustomEntity>>
}