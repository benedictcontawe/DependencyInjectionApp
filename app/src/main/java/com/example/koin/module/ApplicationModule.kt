package com.example.koin.module

import com.example.koin.network.NasaAPI
import com.example.koin.repository.BaseRepository
import com.example.koin.repository.CustomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ApplicationModule {

    @Provides
    @ViewModelScoped
    public fun provideCustomRepository(nasaAPI : NasaAPI) : BaseRepository {
        return CustomRepository(nasaAPI)
    }
    /*
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope
    */
}