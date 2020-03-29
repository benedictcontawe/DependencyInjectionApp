package com.example.koin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin.model.repository.BaseRepository
import com.example.koin.model.web.CountryResponseModel

class MainViewModel : ViewModel {

    private lateinit var baseRepository : BaseRepository

    constructor(baseRepository : BaseRepository) {
        this.baseRepository = baseRepository
    }

    fun requestCountry() : LiveData<List<CountryResponseModel>> {
        return baseRepository.requestCountryDetails()
    }
}