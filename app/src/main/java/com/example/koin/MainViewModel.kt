package com.example.koin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin.model.repository.BaseRepository

class MainViewModel : ViewModel {

    private lateinit var baseRepository : BaseRepository
    private val liveHello : MutableLiveData<String> = MutableLiveData()

    constructor(baseRepository : BaseRepository) {
        this.baseRepository = baseRepository
    }

    fun sayHello() : LiveData<String> {
        try {
            liveHello.setValue("${baseRepository.giveHello()} from $this")
        } catch (ex : Exception) {
            liveHello.setValue("Error! ${ex.message}")
        }
        return liveHello
    }
}