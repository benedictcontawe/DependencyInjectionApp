package com.example.koin.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.koin.AppDelegate
import com.example.koin.R
import com.example.koin.repository.BaseRepository
import com.example.koin.room.CustomDAO
import com.example.koin.room.CustomDatabase
import com.example.koin.room.CustomEntity
import com.example.koin.util.Coroutines
import org.koin.core.module.Module
import org.koin.dsl.module
/**
 * For Database
 * */
object DataModule {

    val module : Module = module {

        single<CustomDatabase> {
            provideRoomDatabase(get<Application>(), "custom_database", get<RoomDatabase.Callback>())
        }

        factory {
            provideRoomDatabaseCallback(get<BaseRepository>())
        }

        factory<CustomDAO> {
            provideCustomDao(get<CustomDatabase>())
        }
    }

    private fun provideRoomDatabase(context : Context, name : String, roomCallback : RoomDatabase.Callback) : CustomDatabase {
        return Room.databaseBuilder(
            context.getApplicationContext(),
            CustomDatabase::class.java,
            name
        )
            .fallbackToDestructiveMigration()
            .addCallback(roomCallback)
            .build()
    }

    private fun provideRoomDatabaseCallback(baseRepository : BaseRepository) : RoomDatabase.Callback {
        return object : RoomDatabase.Callback() {
            override fun onCreate(db : SupportSQLiteDatabase) { //Initialize Database if no database attached to the App
                super.onCreate(db)
                /*Coroutines.io {
                    for (index in 0 until 500) {
                        baseRepository.insert(CustomEntity("name $index", R.drawable.ic_launcher_foreground))
                    }
                }*/
            }

            override fun onOpen(db : SupportSQLiteDatabase) { //Re-open Database if it has database attached to the App
                super.onOpen(db)
            }
        }
    }

    private fun provideCustomDao(database : CustomDatabase) : CustomDAO = database.customDao()
}