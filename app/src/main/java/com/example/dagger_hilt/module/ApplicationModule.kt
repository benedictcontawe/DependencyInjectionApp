package com.example.dagger_hilt.module

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.dagger_hilt.repository.BaseRepository
import com.example.dagger_hilt.repository.CustomRepository
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
    public fun provideCustomRepository(dataStore  : DataStore<Preferences>) : BaseRepository {
        return CustomRepository(dataStore)
    }
    /*
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope
    */
}