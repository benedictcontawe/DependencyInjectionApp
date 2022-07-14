package com.example.koin.module

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.koin.AppDelegate
import com.example.koin.room.CustomDAO
import com.example.koin.room.CustomDatabase
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * For Database
 * */
object DataModule {

    val module : Module = module {

        single<CustomDatabase> {
            provideCustomDatabase(get<RoomDatabase.Callback>())
        }

        factory {
            provideRoomDatabaseCallback()
        }

        factory<CustomDAO> {
            provideCustomDao(get<CustomDatabase>())
        }
    }

    private fun provideCustomDatabase(callback : RoomDatabase.Callback) : CustomDatabase {
        return Room.databaseBuilder(
            AppDelegate.applicationContext(),
            CustomDatabase::class.java, "custom_database"
        )
            .fallbackToDestructiveMigration()
            .addCallback(callback)
            .build()
    }

    private fun provideRoomDatabaseCallback() : RoomDatabase.Callback {
        return object : RoomDatabase.Callback() {
            override fun onCreate(db : SupportSQLiteDatabase) {
                super.onCreate(db) //Initialize Database if no database attached to the App
            }

            override fun onOpen(db : SupportSQLiteDatabase) {
                super.onOpen(db) //Re-open Database if it has database attached to the App
            }
        }
    }

    private fun provideCustomDao(database : CustomDatabase) : CustomDAO = database.customDao()
}