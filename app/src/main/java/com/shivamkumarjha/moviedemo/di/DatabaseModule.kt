package com.shivamkumarjha.moviedemo.di

import android.content.Context
import androidx.room.Room
import com.shivamkumarjha.moviedemo.config.Constants
import com.shivamkumarjha.moviedemo.persistence.AppDatabase
import com.shivamkumarjha.moviedemo.persistence.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, Constants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun movieDao(appDatabase: AppDatabase): MovieDao = appDatabase.movieDao()

}