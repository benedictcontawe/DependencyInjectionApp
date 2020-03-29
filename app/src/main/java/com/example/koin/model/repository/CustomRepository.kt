package com.example.koin.model.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.koin.model.room.CustomDAO
import com.example.koin.model.room.CustomEntity
import org.koin.core.inject
import org.koin.core.KoinComponent

class CustomRepository() : BaseRepository, KoinComponent {

    private val customDao : CustomDAO by inject()

    override fun insert(customEntity: CustomEntity) {
        AsyncTask.execute {
            customDao.insert(
                customEntity
            )
        }
    }

    override fun update(customEntity: CustomEntity) {
        println("${customEntity.id}")
        AsyncTask.execute {
            customDao.update(
                customEntity
            )
        }
    }

    override fun delete(customEntity: CustomEntity) {
        println("${customEntity.id}")
        AsyncTask.execute {
            customDao.delete(
                customEntity.id
            )
        }
    }

    override fun deleteAll() {
        AsyncTask.execute {
            customDao.deleteAll()
        }
    }

    override fun getAll(): LiveData<List<CustomEntity>> {
        return customDao.getAll()
    }
}
