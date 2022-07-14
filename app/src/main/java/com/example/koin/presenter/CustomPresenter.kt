package com.example.koin.presenter

import com.example.koin.repository.BaseRepository

class CustomPresenter {

    private lateinit var  customRepository : BaseRepository

    constructor(baseRepository :  BaseRepository) {
        this.customRepository = baseRepository
    }

    fun sayHello() = "${customRepository.getAll()} from $this"

}