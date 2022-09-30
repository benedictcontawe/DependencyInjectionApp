package com.example.koin.presenter

import com.example.koin.repository.BaseRepository

public class CustomPresenter {

    private val  customRepository : BaseRepository

    constructor(baseRepository :  BaseRepository) {
        this.customRepository = baseRepository
    }

    //fun sayHello() = "${customRepository.getAll()} from $this"
}