package com.example.koin.model.web

import retrofit2.Call
import retrofit2.http.GET

interface CountryAPI {
    //https://restcountries.eu/rest/v2/all
    @GET("/rest/v2/all")
    fun getCountryDetails() : Call<List<CountryResponseModel>>
}