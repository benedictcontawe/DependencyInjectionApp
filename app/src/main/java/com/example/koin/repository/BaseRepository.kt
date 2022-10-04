package com.example.koin.repository

import kotlinx.coroutines.flow.Flow

public interface BaseRepository {

    public fun giveRepository() : String

    public suspend fun update(booleanKey : Boolean)

    public suspend fun update(stringKey : String)

    public suspend fun update(integerKey : Int)

    public suspend fun update(doubleKey : Double)

    public suspend fun update(longKey : Long)

    public fun getBoolean() : Flow<Boolean>

    public fun getString() : Flow<String>

    public fun getInteger() : Flow<Int>

    public fun getDouble() : Flow<Double>

    public fun getLong() : Flow<Long>

}