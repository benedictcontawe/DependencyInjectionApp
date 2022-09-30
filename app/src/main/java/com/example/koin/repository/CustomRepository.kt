package com.example.koin.repository

import android.util.Log
import com.example.jetpackcomponentsapp.NasaRequestModel
import com.example.jetpackcomponentsapp.NasaResponseModel
import com.example.koin.network.NasaAPI
import retrofit2.Response

public class CustomRepository : BaseRepository {

    companion object {
        private val TAG : String = CustomRepository::class.java.getSimpleName()
    }

    private val nasaAPI : NasaAPI

    constructor(nasaAPI : NasaAPI) {
        this.nasaAPI = nasaAPI
    }

    override fun giveRepository() : String {
        return this.toString()
    }

    public override suspend fun getAPOD(request : NasaRequestModel) : List<NasaResponseModel> {
        val response : Response<List<NasaResponseModel>> = nasaAPI.getAstronomyPictureOfTheDay(request.key!!, request.count!!).execute()
        Log.d(TAG,"isSuccessful() ${response.isSuccessful()}")
        Log.d(TAG,"errorBody() ${response.errorBody()}")
        Log.d(TAG,"body() ${response.body()}")
        Log.d(TAG,"code() ${response.code()}")
        Log.d(TAG,"headers() ${response.headers()}")
        Log.d(TAG,"message() ${response.message()}")
        Log.d(TAG,"raw() ${response.raw()}")
        return if (response.isSuccessful() && response.body() != null) response.body()!!
        else if (!response.isSuccessful()) listOf<NasaResponseModel>()
        else emptyList<NasaResponseModel>()
    }
}