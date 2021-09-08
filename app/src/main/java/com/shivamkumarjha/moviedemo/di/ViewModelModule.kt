package com.shivamkumarjha.moviedemo.di

import com.shivamkumarjha.moviedemo.network.ApiService
import com.shivamkumarjha.moviedemo.persistence.MovieDao
import com.shivamkumarjha.moviedemo.repository.MovieRepository
import com.shivamkumarjha.moviedemo.repository.MovieRepositoryImpl
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
    fun getMovieRepository(
        apiService: ApiService,
        movieDao: MovieDao,
        @IoDispatcher ioDispatcher: CoroutineDispatcher
    ): MovieRepository {
        return MovieRepositoryImpl(apiService, movieDao, ioDispatcher)
    }

}