package com.example.dependencyinjectionapp.view.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.dependencyinjectionapp.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainAndroidViewModel : AndroidViewModel {

    companion object {
        private val TAG : String = MainAndroidViewModel::class.java.simpleName
    }

    private val  baseRepository : BaseRepository

    @Inject
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