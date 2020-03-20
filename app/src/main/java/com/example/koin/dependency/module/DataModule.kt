package com.example.koin.dependency.module

import org.koin.dsl.module

/**
 * For Database
 * */
object DataModule {

    val dataModule = module {

        //fun provideApi(retrofit: Retrofit) = retrofit.create(GithubRepoApi::class.java)

        /*
        single {
            provideApi(get())
        }
        */
    }
}