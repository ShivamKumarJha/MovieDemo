package com.shivamkumarjha.moviedemo.ui.main

import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.shivamkumarjha.moviedemo.config.Constants
import com.shivamkumarjha.moviedemo.model.Result
import com.shivamkumarjha.moviedemo.utility.DateUtility

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MovieItem(movie: Result, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    val ratingBar = remember {
        RatingBar(context).apply {
            progress = movie.vote_average.toInt()
        }
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        modifier = modifier
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(6.dp)
        ) {

            Image(
                painter = rememberImagePainter(
                    data = "${Constants.IMAGE_PREFIX_URL}${movie.poster_path}"
                ),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)
                    .width(100.dp)
            )

            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .weight(1f)
            ) {

                Text(movie.title, fontSize = 16.sp)

                Text(text = DateUtility.getRelativeTime(movie.release_date) ?: movie.release_date)

                Text(text = movie.popularity.toString())

                Text(
                    text = movie.overview,
                    maxLines = 2,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis
                )

                AndroidView(factory = { ratingBar })

            }

        }

    }
}