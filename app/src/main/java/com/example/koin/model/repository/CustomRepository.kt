package com.example.koin.model.repository

class CustomRepository() : BaseRepository {

    override fun giveHello() : String {
        return "Hello Koin"
    }
}