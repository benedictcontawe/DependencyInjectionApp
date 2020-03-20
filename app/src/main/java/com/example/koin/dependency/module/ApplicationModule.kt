package com.example.koin.dependency.module

import com.example.koin.model.repository.BaseRepository
import com.example.koin.model.repository.CustomRepository
import com.example.koin.presenter.CustomPresenter
import org.koin.core.module.Module
import org.koin.dsl.module

object ApplicationModule {

    val applicationModule : Module = module {

        single<BaseRepository> {
            CustomRepository()
        }

        // Simple Presenter Factory
        factory {
            CustomPresenter(get())
            //provideCustomPresenter()
        }
    }

    //fun provideCustomRepository() : CustomRepository = CustomRepository()

    //fun provideCustomPresenter() : CustomPresenter = CustomPresenter()
}