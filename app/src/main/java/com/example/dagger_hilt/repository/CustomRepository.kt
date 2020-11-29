package com.example.dagger_hilt.repository

class CustomRepository : BaseRepository {

    override fun giveRepository() : String {
        return this.toString()
    }

    override fun giveHello() : String {
        return "Hello Dagger Hilt"
    }
}