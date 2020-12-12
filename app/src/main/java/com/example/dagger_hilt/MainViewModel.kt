package com.example.dagger_hilt

import androidx.hilt.lifecycle.ViewModelInject
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

    fun getInstance() : String {
        return this.toString()
    }

    fun getRepositoryInstance() : String {
        return baseRepository.giveRepository()
    }
}