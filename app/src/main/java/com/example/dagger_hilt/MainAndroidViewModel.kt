package com.example.dagger_hilt

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import com.example.dagger_hilt.repository.BaseRepository

class MainAndroidViewModel : AndroidViewModel {

    private val  baseRepository : BaseRepository

    @ViewModelInject
    constructor(baseRepository : BaseRepository, application: Application) : super(application) {
        this.baseRepository = baseRepository
    }

    fun getInstance() : String {
        return this.toString()
    }

    fun getRepositoryInstance() : String {
        return baseRepository.giveRepository()
    }

}