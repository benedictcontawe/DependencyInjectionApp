package com.example.dagger_hilt.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CustomEntity::class],
    version = 1
)
abstract class CustomDatabase : RoomDatabase() {

    abstract fun customDao() : CustomDAO

}