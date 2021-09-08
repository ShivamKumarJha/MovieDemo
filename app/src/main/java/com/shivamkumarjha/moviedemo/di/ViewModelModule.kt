package com.shivamkumarjha.moviedemo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun getAirlineRepository(
        apiService: ApiService,
        airlineDao: AirlineDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): AirlineRepository {
        return AirlineRepositoryImpl(apiService, airlineDao, ioDispatcher)
    }

}