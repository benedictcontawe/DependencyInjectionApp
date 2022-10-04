package com.example.koin.view.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.koin.repository.BaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainAndroidViewModel : AndroidViewModel {

    private val repository : BaseRepository

    @Inject
    constructor(repository : BaseRepository, application: Application) : super(application) {
        this.repository = repository
    }
}