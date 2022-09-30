package com.example.koin.network

import com.example.jetpackcomponentsapp.NasaResponseModel
import com.example.koin.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

public interface NasaAPI { //https://api.nasa.gov/planetary/apod?api_key=xxxxx&count=###
    @GET(Constants.API_GET) //@HTTP(method = "GET", path = Constants.API_GET, hasBody = true)
    public fun getAstronomyPictureOfTheDay( //@Body model : NasaRequestModel
        @Query("api_key") key : String,
        @Query("count") count : Int
    ) : Call<List<NasaResponseModel>>
}