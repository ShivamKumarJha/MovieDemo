package com.shivamkumarjha.moviedemo.repository

import android.util.Log
import com.shivamkumarjha.moviedemo.config.Constants.TAG
import com.shivamkumarjha.moviedemo.di.IoDispatcher
import com.shivamkumarjha.moviedemo.model.MovieResponse
import com.shivamkumarjha.moviedemo.network.ApiService
import com.shivamkumarjha.moviedemo.network.NoConnectivityException
import com.shivamkumarjha.moviedemo.network.Resource
import com.shivamkumarjha.moviedemo.persistence.MovieDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MovieRepositoryImpl(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MovieRepository {

    override suspend fun getPopularMovies(sort_by: String): Flow<Resource<MovieResponse?>> = flow {
        emit(Resource.loading(null))
        try {
            val response = apiService.getPopularMovies(sort_by)
            if (response.isSuccessful) {
                val data = response.body()
                emit(Resource.success(data))
            } else {
                emit(Resource.error(null, response.code().toString()))
            }
        } catch (exception: Exception) {
            if (exception is NoConnectivityException) {
                emit(Resource.offline(null))
            } else {
                emit(Resource.error(null, exception.localizedMessage))
                Log.e(TAG, exception.localizedMessage ?: "")
            }
        }
    }.flowOn(ioDispatcher)

}