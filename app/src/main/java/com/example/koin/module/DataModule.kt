package com.example.koin.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    /*
    @Provides
    @Singleton
    public fun provideDataStore(baseContext : Context) : DataStore<Preferences> {//data_store
        //return application.getBaseContext().createDataStore(name = "data_store")
        val baseContext.dataStore : DataStore<Preferences> by preferencesDataStore(name = "data_store")
        return dataStore
    }

    @Provides
    @Singleton
    public fun provideDataBase(application : Application, roomCallback : RoomDatabase.Callback) : CustomDatabase {
        return Room.databaseBuilder(
            application.getApplicationContext(),
            CustomDatabase::class.java,
            "custom_database"
        )
            .fallbackToDestructiveMigration()
            .addCallback(roomCallback)
            .build()
    }

    @Provides
    public fun provideRoomDatabaseCallback() : RoomDatabase.Callback {
        return object : RoomDatabase.Callback() {
            override fun onCreate(db : SupportSQLiteDatabase) {
                //Initialize Database if no database attached to the App
                super.onCreate(db)
            }

            override fun onOpen(db : SupportSQLiteDatabase) {
                //Re-open Database if it has database attached to the App
                super.onOpen(db)
            }
        }
    }

    @Provides
    public fun provideCustomDAO(customDatabase : CustomDatabase) : CustomDAO {
        return customDatabase.customDao()
    }
    */
}