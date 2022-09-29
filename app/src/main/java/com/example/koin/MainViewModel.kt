package com.example.koin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin.model.CustomModel
import com.example.koin.repository.BaseRepository
import com.example.koin.util.ConvertList
import com.example.koin.util.Coroutines
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewModel : ViewModel(), KoinComponent {

    private val customRepository : BaseRepository by inject()
    private val liveUpdate : MutableLiveData<CustomModel> by lazy(LazyThreadSafetyMode.NONE, initializer = { MutableLiveData<CustomModel>() })

    fun setUpdate(item : CustomModel) {
        liveUpdate.value = item
    }

    fun getUpdate() : LiveData<CustomModel> {
        return liveUpdate
    }

    fun insertItem(item : CustomModel) {
        Coroutines.io(this@MainViewModel, work = {
            customRepository.insert(
                ConvertList.toEntity(item)
            )
        })
    }

    fun updateItem() {
        Coroutines.io(this@MainViewModel, work = {
            liveUpdate.value?.let {
                customRepository.update(
                    ConvertList.toEntity(it)
                )
            }
        })
    }

    fun deleteItem(item : CustomModel) {
        Coroutines.io(this@MainViewModel, work = {
            customRepository.delete(
                ConvertList.toEntity(item)
            )
        })
    }

    fun deleteAll() {
        Coroutines.io(this@MainViewModel, work = {
            customRepository.deleteAll()
        })
    }

    fun observeItems() : LiveData<MutableList<CustomModel>> {
        return ConvertList.toLiveDataListModel(
            customRepository.getAll()
        )
    }
}