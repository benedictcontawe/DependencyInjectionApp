package com.example.dagger_hilt.module

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.dagger_hilt.room.CustomDAO
import com.example.dagger_hilt.room.CustomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataModule {

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

}