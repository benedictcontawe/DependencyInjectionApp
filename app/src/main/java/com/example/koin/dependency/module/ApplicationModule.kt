package com.example.koin.dependency.module

import android.app.Application
import com.example.koin.MainAndroidViewModel
import com.example.koin.MainViewModel
import com.example.koin.model.repository.BaseRepository
import com.example.koin.model.repository.CustomRepository
import com.example.koin.presenter.CustomPresenter
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * For Application (View Model, Presenter, App)
 * */
object ApplicationModule {

    val applicationModule : Module = module {

        single<BaseRepository> {
            provideCustomRepository()
        }

        viewModel {
            provideMainViewModel(get<BaseRepository>())
        }

        viewModel {
            provideMainAndroidViewModel(get<Application>(),get<BaseRepository>())
        }
    }

    fun provideCustomRepository() : CustomRepository = CustomRepository()

    fun provideMainViewModel(baseRepository : BaseRepository) : MainViewModel = MainViewModel()

    fun provideMainAndroidViewModel(application: Application,baseRepository : BaseRepository) : MainAndroidViewModel = MainAndroidViewModel(application)
}
