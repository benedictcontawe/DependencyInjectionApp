package com.example.dagger_hilt

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger_hilt.repository.BaseRepository

class MainViewModel : ViewModel {

    private val  baseRepository : BaseRepository
    private val liveHello : MutableLiveData<String> by lazy(LazyThreadSafetyMode.NONE, initializer = { MutableLiveData<String>() })

    @ViewModelInject
    constructor(baseRepository : BaseRepository) {
        this.baseRepository = baseRepository
    }

    fun sayHello() : LiveData<String> {
        try {
            liveHello.setValue("${baseRepository.giveHello()} \n from \n MainViewModel Instance \n $this \n and \n CustomRepository Instance \n ${baseRepository.giveRepository()}")
        } catch (ex : Exception) {
            liveHello.setValue("Error! ${ex.message}")
        }
        return liveHello
    }
}