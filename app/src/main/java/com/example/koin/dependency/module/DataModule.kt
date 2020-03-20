package com.example.koin.dependency.module

import org.koin.dsl.module

object DataModule {

    val apiModule = module {

        //fun provideApi(retrofit: Retrofit) = retrofit.create(GithubRepoApi::class.java)

        /*
        single {
            provideApi(get())
        }
        */
    }
}