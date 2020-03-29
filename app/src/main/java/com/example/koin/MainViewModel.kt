package com.example.koin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin.model.CustomModel
import com.example.koin.model.repository.BaseRepository
import com.example.koin.util.ConvertList

class MainViewModel : ViewModel {

    private val customRepository : BaseRepository
    private lateinit var liveList : LiveData<MutableList<CustomModel>>
    private lateinit var liveUpdate : MutableLiveData<CustomModel>

    constructor(baseRepository : BaseRepository) {
        this.customRepository = baseRepository
        liveList = MutableLiveData()
        liveUpdate = MutableLiveData()
    }

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
        liveList = ConvertList.toLiveDataListModel(
            customRepository.getAll()
        )
        return liveList
    }
}