package com.example.koin.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.koin.model.web.CountryAPI
import com.example.koin.model.web.CountryResponseModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomRepository : BaseRepository, KoinComponent {

    private val countryAPI : CountryAPI by inject()

    override fun requestCountryDetails() : LiveData<List<CountryResponseModel>> {
        val call = countryAPI.getCountryDetails()
        val countryResponseLiveList : MutableLiveData<List<CountryResponseModel>> = MutableLiveData()

        call.enqueue(object : Callback<List<CountryResponseModel>> {

            override fun onResponse(call : Call<List<CountryResponseModel>>, response : Response<List<CountryResponseModel>>) {
                if (!response.isSuccessful) {
                    Log.e("Test - CustomRepository","!response.isSuccessful")
                    return
                }
                Log.e("Test - CustomRepository","response.isSuccessful")
                countryResponseLiveList.value = response.body()!!
            }

            override fun onFailure(call : Call<List<CountryResponseModel>>, throwable : Throwable) {
                Log.e("Test - CustomRepository","Callback $throwable")
                countryResponseLiveList.value = null
            }
        })

        return countryResponseLiveList
    }
}