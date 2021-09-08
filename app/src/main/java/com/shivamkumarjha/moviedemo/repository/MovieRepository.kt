package com.shivamkumarjha.moviedemo.repository

import com.shivamkumarjha.moviedemo.model.MovieResponse
import com.shivamkumarjha.moviedemo.network.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(sort_by: String, page: Int): Flow<Resource<MovieResponse?>>
}