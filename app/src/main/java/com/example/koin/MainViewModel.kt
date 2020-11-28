package com.example.koin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin.model.CustomModel
import com.example.koin.model.repository.BaseRepository
import com.example.koin.util.ConvertList
import org.koin.core.KoinComponent
import org.koin.core.inject

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
        customRepository.insert(
            ConvertList.toEntity(item)
        )
    }

    fun updateItem() {
        liveUpdate.value?.let {
            customRepository.update(
                ConvertList.toEntity(it)
            )
        }
    }

    fun deleteItem(item : CustomModel) {
        customRepository.delete(
            ConvertList.toEntity(item)
        )
    }

    fun deleteAll() {
        customRepository.deleteAll()
    }

    fun getItems() : LiveData<MutableList<CustomModel>> {
        return ConvertList.toLiveDataListModel(
            customRepository.getAll()
        )
    }
}