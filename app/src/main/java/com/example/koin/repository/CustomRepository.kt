package com.example.koin.repository

import androidx.lifecycle.LiveData
import com.example.koin.room.CustomDAO
import com.example.koin.room.CustomEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CustomRepository() : BaseRepository, KoinComponent {

    private val customDao : CustomDAO by inject()

    override suspend fun insert(customEntity : CustomEntity) {
        customDao.insert(
            customEntity
        )
    }

    override suspend fun update(customEntity : CustomEntity) {
        println("${customEntity.id}")
        customDao.update(
            customEntity
        )
    }

    override suspend fun delete(customEntity : CustomEntity) {
        println("${customEntity.id}")
        customDao.delete(
            customEntity.id
        )
    }

    override suspend fun deleteAll() {
        customDao.deleteAll()
    }

    override fun getAll() : LiveData<List<CustomEntity>> {
        return customDao.getAll()
    }
}
