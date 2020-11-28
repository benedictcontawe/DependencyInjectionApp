package com.example.koin.dependency.module

import com.example.koin.model.room.CustomDAO
import com.example.koin.model.room.CustomDatabase
import org.koin.dsl.module

/**
 * For Database
 * */
object DataModule {

    val dataModule = module {

        single<CustomDatabase> {
            provideCustomDatabase()
        }

        factory<CustomDAO> {
            provideCustomDao()
        }
    }

    private fun provideCustomDatabase() : CustomDatabase = CustomDatabase.getInstance()!!

    private fun provideCustomDao() : CustomDAO = CustomDatabase.getInstance()!!.customDao()
}