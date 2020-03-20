package com.example.koin.dependency.module

import com.example.koin.model.repository.BaseRepository
import com.example.koin.model.repository.CustomRepository
import com.example.koin.presenter.CustomPresenter
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * For Database (Realm, Room, SQLite)
 * */
object ApplicationModule {

    val applicationModule : Module = module {

        single<BaseRepository> {
            provideCustomRepository()
        }

        // Simple Presenter Factory
        factory {
            provideCustomPresenter(get())
        }
    }

    fun provideCustomRepository() : CustomRepository = CustomRepository()

    fun provideCustomPresenter(baseRepository :  BaseRepository) : CustomPresenter = CustomPresenter(baseRepository)
}
