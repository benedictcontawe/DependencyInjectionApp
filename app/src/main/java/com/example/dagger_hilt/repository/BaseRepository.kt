package com.example.dagger_hilt.repository

import kotlinx.coroutines.flow.Flow

interface BaseRepository {

    fun giveRepository() : String

    suspend fun update(booleanKey : Boolean)

    suspend fun update(stringKey : String)

    suspend fun update(integerKey : Int)

    suspend fun update(doubleKey : Double)

    suspend fun update(longKey : Long)

    fun getBoolean() : Flow<Boolean>

    fun getString() : Flow<String>

    fun getInteger() : Flow<Int>

    fun getDouble() : Flow<Double>

    fun getLong() : Flow<Long>
}