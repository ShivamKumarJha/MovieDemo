package com.shivamkumarjha.moviedemo.ui.main

import androidx.lifecycle.*
import com.shivamkumarjha.moviedemo.config.Constants
import com.shivamkumarjha.moviedemo.di.IoDispatcher
import com.shivamkumarjha.moviedemo.model.MovieResponse
import com.shivamkumarjha.moviedemo.network.Resource
import com.shivamkumarjha.moviedemo.persistence.MovieDao
import com.shivamkumarjha.moviedemo.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieDao: MovieDao,
    private val movieRepository: MovieRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    val dbMovies = liveData {
        emitSource(movieDao.getPopularMovies())
    }

    private val _moviesResponse = MutableLiveData<Resource<MovieResponse?>>()
    val moviesResponse: LiveData<Resource<MovieResponse?>> = _moviesResponse

    init {
        callApi()
    }

    fun callApi(page: Int = 1) {
        viewModelScope.launch(ioDispatcher) {
            movieRepository.getPopularMovies(Constants.API_SORT_QUERY, page).collect {
                _moviesResponse.postValue(it)
            }
        }
    }

}