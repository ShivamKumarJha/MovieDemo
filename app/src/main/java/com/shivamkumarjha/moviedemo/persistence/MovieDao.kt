package com.shivamkumarjha.moviedemo.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.shivamkumarjha.moviedemo.model.Result

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addResult(result: Result)

    @Query("DELETE from movie_results")
    suspend fun clearResults()

    @Delete
    suspend fun deleteResult(result: Result)

    @Query("SELECT * FROM movie_results ORDER BY page ASC")
    fun getPopularMovies(): LiveData<List<Result>>

}