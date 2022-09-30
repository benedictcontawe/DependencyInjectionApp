package com.example.koin.repository

import com.example.jetpackcomponentsapp.NasaRequestModel
import com.example.jetpackcomponentsapp.NasaResponseModel

public interface BaseRepository {

    public fun giveRepository() : String

    public suspend fun getAPOD(request : NasaRequestModel) : List<NasaResponseModel>
}