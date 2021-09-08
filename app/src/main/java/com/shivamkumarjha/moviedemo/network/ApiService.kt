package com.shivamkumarjha.moviedemo.network

import com.shivamkumarjha.moviedemo.config.Constants
import com.shivamkumarjha.moviedemo.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getPopularMovies(
        @Query("sort_by") sort_by: String,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = Constants.API_KEY,
    ): Response<MovieResponse>

}