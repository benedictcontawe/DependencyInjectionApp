package com.example.dagger_hilt.module

import com.example.dagger_hilt.repository.BaseRepository
import com.example.dagger_hilt.repository.CustomRepository
import com.example.dagger_hilt.room.CustomDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    public fun provideCustomRepository(customDao : CustomDAO) : BaseRepository {
        return CustomRepository(customDao)
    }
    /*
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope
    */
}