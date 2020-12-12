package com.example.dagger_hilt.repository

import androidx.lifecycle.LiveData
import com.example.dagger_hilt.room.CustomDAO
import com.example.dagger_hilt.room.CustomEntity
import javax.inject.Inject

class CustomRepository : BaseRepository {

    private val customDao : CustomDAO

    @Inject
    constructor (customDao : CustomDAO) {
        this.customDao = customDao
    }


    override fun giveRepository() : String {
        return this.toString()
    }
    //region CRUD Operation
    override suspend fun insert(customEntity : CustomEntity) {
        customDao.insert(
            customEntity
        )
    }

    override suspend fun update(customEntity : CustomEntity) {
        customDao.update(
            customEntity
        )
    }

    override suspend fun delete(customEntity: CustomEntity) {
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
    //endregion
}