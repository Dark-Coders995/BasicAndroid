package com.develop.basicandroid.movieapp3.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.develop.basicandroid.movieapp3.models.Movie
import com.develop.basicandroid.movieapp3.models.getMovies
import com.develop.basicandroid.movieapp3.navigation.MovieScreens
import com.develop.basicandroid.movieapp3.widgets.MovieRow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Movies")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            MainContent(navController = navController)
        }
    }
}

@Composable
fun MainContent(
    navController: NavController,
    moviesList: List<Movie> = getMovies()
) {
    Column(
        modifier = Modifier
            .padding(12.dp)
    ) {
        LazyColumn {
            items(items = moviesList) {
                MovieRow(
                    movie = it
                ){movieId ->
                    Log.d("TAG", "Contents: $movieId")
                    navController.navigate(
                        route = MovieScreens.DetailsScreen.name +"/$movieId"
                    )
                }
            }

        }
    }
}

