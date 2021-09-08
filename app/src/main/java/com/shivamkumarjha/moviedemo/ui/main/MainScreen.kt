package com.shivamkumarjha.moviedemo.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shivamkumarjha.moviedemo.network.Resource
import com.shivamkumarjha.moviedemo.network.Status

@Composable
fun MainScreen() {
    val viewModel: MainViewModel = viewModel()

    val dbMovies = viewModel.dbMovies.observeAsState(null)
    val moviesResponse = viewModel.moviesResponse.observeAsState(Resource.loading(null))

    Scaffold(
        bottomBar = {
            if (moviesResponse.value.status == Status.LOADING) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
        }
    ) {

        LazyColumn {
            items(dbMovies.value ?: listOf()) { movie ->
                MovieItem(
                    movie,
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
        }

    }
}