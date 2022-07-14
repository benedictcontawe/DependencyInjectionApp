package com.example.koin.module

import android.app.Application
import com.example.koin.MainAndroidViewModel
import com.example.koin.MainViewModel
import com.example.koin.repository.BaseRepository
import com.example.koin.repository.CustomRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * For Application (View Model, Presenter, App)
 * */
object ApplicationModule {

    val module : Module = module {

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

    private fun provideCustomRepository() : CustomRepository = CustomRepository()

    private fun provideMainViewModel(baseRepository : BaseRepository) : MainViewModel = MainViewModel()

    private fun provideMainAndroidViewModel(application: Application, baseRepository : BaseRepository) : MainAndroidViewModel = MainAndroidViewModel(application)
}
