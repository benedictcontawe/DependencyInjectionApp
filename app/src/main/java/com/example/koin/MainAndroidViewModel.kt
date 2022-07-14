package com.example.koin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.koin.repository.BaseRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainAndroidViewModel : AndroidViewModel, KoinComponent {

    private val baseRepository : BaseRepository by inject()
    private val liveHello : MutableLiveData<String> by lazy(LazyThreadSafetyMode.NONE, initializer = { MutableLiveData<String>() })

    constructor(application: Application) : super(application) {

    }

}