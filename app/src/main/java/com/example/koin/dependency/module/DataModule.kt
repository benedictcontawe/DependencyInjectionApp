package com.example.koin.dependency.module

import android.content.Context
import com.example.koin.model.room.CustomDAO
import com.example.koin.model.room.CustomDatabase
import org.koin.dsl.module

/**
 * For Database
 * */
object DataModule {

    val dataModule = module {

        single<CustomDatabase> {
            provideCustomDatabase(get<Context>())
        }

        factory<CustomDAO> {
            provideCustomDao(get<Context>())
        }
    }

    private fun provideCustomDatabase(context: Context) : CustomDatabase = CustomDatabase.getInstance(context)!!

    private fun provideCustomDao(context: Context) : CustomDAO = CustomDatabase.getInstance(context)!!.customDao()
}