package com.develop.basicandroid.movieapp3.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.develop.basicandroid.movieapp3.screens.home.HomeScreen
import com.develop.basicandroid.movieapp3.screens.details.DetailsScreen

@Composable
fun MovieNavigation(){
    val navController = rememberNavController() // Nav Controller
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name // Nav Host
    ){
        // nav Graph
        composable(
            MovieScreens.HomeScreen.name
        ){
            // Here we pass where this should lead us to
            HomeScreen(navController = navController)
        }

        // Linking like web  www.ggogle/detials/id=34
         composable(
             route = MovieScreens.DetailsScreen.name + "/{movieId}",
             arguments = listOf(navArgument(name = "movieId"){
                 type = NavType.StringType
             })
         ) {
             backStackEntry ->
             DetailsScreen(
                 navController = navController,
                 backStackEntry.arguments?.getString("movieId")
             )
         }
    }
}