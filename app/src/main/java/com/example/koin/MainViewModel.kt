package com.example.koin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin.model.repository.BaseRepository
import com.example.koin.model.web.CountryResponseModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel : ViewModel(), KoinComponent {

    private val baseRepository : BaseRepository by inject()

    fun requestCountry() : LiveData<List<CountryResponseModel>> {
        return baseRepository.requestCountryDetails()
    }
}