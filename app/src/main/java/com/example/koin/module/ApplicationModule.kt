package com.example.koin.module

import android.content.Context
import com.example.koin.repository.BaseRepository
import com.example.koin.repository.CustomRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    public fun provideCustomRepository(@ApplicationContext context : Context) : BaseRepository {
        return CustomRepository(context)
    }
    /*
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope
    */
}