package com.example.koin.dependency.module

import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * For API (Retrofit)
 * */
object APIModule {

    val apiModule = module {

        //fun provideApi(retrofit: Retrofit) = retrofit.create(GithubRepoApi::class.java)

        /*
        single {
            provideApi(get())
        }
        */
    }
}