package com.example.koin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.koin.model.repository.BaseRepository
import com.example.koin.model.web.CountryResponseModel

class MainAndroidViewModel : AndroidViewModel {

    private lateinit var baseRepository : BaseRepository
    private val liveHello : MutableLiveData<String> = MutableLiveData()

    constructor(application: Application) : super(application) {

    }

    constructor(application: Application,baseRepository : BaseRepository) : super(application) {
        this.baseRepository = baseRepository
    }

    fun requestCountry() : LiveData<List<CountryResponseModel>> {
        return baseRepository.requestCountryDetails()
    }
}