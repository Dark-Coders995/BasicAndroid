package com.develop.basicandroid.movieapp3

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.develop.basicandroid.movieapp3.navigation.MovieNavigation
import com.develop.basicandroid.ui.theme.AppTheme

@Composable
fun MyApp3(
    content: @Composable () -> Unit
) {
    AppTheme {
        content()
    }
}

@Preview
@Composable
fun App3(){
    MovieNavigation()
}




