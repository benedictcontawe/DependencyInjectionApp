package com.example.koin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin.model.repository.BaseRepository
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel : ViewModel(), KoinComponent {

    private val baseRepository : BaseRepository by inject()
    private val liveHello : MutableLiveData<String> by lazy(LazyThreadSafetyMode.NONE, initializer = { MutableLiveData<String>() })

    fun sayHello() : LiveData<String> {
        try {
            liveHello.setValue("${baseRepository.giveHello()} \n from \n $this \n and \n ${baseRepository.giveRepository()}")
        } catch (ex : Exception) {
            liveHello.setValue("Error! ${ex.message}")
        }
        return liveHello
    }
}