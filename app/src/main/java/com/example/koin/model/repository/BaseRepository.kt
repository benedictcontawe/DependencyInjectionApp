package com.example.koin.model.repository

import androidx.lifecycle.LiveData
import com.example.koin.model.web.CountryResponseModel

interface BaseRepository {

    fun requestCountryDetails() : LiveData<List<CountryResponseModel>>
}