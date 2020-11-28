package com.example.koin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.koin.model.repository.BaseRepository
import com.example.koin.model.web.CountryResponseModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainAndroidViewModel : AndroidViewModel, KoinComponent {

    private val baseRepository : BaseRepository by inject()
    private val liveHello : MutableLiveData<String> by lazy(LazyThreadSafetyMode.NONE, initializer = { MutableLiveData<String>() })

    constructor(application: Application) : super(application) {

    }

    fun requestCountry() : LiveData<List<CountryResponseModel>> {
        return baseRepository.requestCountryDetails()
    }
}