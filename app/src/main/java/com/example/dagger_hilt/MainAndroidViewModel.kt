package com.example.dagger_hilt

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dagger_hilt.repository.BaseRepository

class MainAndroidViewModel : AndroidViewModel {

    private val  baseRepository : BaseRepository
    private val liveHello : MutableLiveData<String> by lazy(LazyThreadSafetyMode.NONE, initializer = { MutableLiveData<String>() })

    @ViewModelInject
    constructor(baseRepository : BaseRepository, application: Application) : super(application) {
        this.baseRepository = baseRepository
    }

    fun sayHello() : LiveData<String> {
        try {
            liveHello.setValue("${baseRepository.giveHello()} \n from \n MainAndroidViewModel Instance \n $this \n and \n CustomRepository Instance \n ${baseRepository.giveRepository()}")
        } catch (ex : Exception) {
            liveHello.setValue("Error! ${ex.message}")
        }
        return liveHello
    }
}