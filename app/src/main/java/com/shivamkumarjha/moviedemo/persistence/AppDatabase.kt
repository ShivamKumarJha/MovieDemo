package com.shivamkumarjha.moviedemo.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shivamkumarjha.moviedemo.model.Result

@Database(
    entities = [
        Result::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}