package com.example.koin.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.koin.room.CustomDAO
import com.example.koin.room.CustomEntity
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

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
