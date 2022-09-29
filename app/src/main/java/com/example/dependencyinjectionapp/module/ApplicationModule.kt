package com.example.dependencyinjectionapp.module

import com.example.dependencyinjectionapp.repository.BaseRepository
import com.example.dependencyinjectionapp.repository.CustomRepository
import com.example.dependencyinjectionapp.room.CustomDAO
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
    public fun provideCustomRepository(customDao : CustomDAO) : BaseRepository {
        return CustomRepository(customDao)
    }
    /*
    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class ApplicationScope
    */
}