package com.example.koin.presenter

import com.example.koin.model.repository.BaseRepository
import com.example.koin.model.repository.CustomRepository
import org.koin.core.KoinApplication

class CustomPresenter {

    private lateinit var  customRepository : BaseRepository

    constructor(baseRepository :  BaseRepository) {
        this.customRepository = baseRepository
    }

    fun sayHello() = "${customRepository.requestCountryDetails()} from $this"

}