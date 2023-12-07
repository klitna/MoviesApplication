package com.iryna.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.iryna.moviesapp.presentation.splash.SplashScreen
import androidx.compose.runtime.Composable
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.iryna.moviesapp.presentation.error.ErrorScreen
import com.iryna.moviesapp.presentation.navigation.NavItem
import com.iryna.moviesapp.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import com.iryna.moviesapp.presentation.moviedetails.WorkerDetails
import com.iryna.moviesapp.presentation.movies.MoviesScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppTheme {
                WorkersApp(navController)
            }
        }
    }
}

@Composable
fun WorkersApp(navController: NavHostController) {

    NavHost(navController, startDestination = NavItem.SPLASH.route) {
        composable(NavItem.SPLASH.route) {
            SplashScreen(onAnimationFinished = { navController.navigate(NavItem.MOVIES.route) })
        }
        composable(NavItem.MOVIES.route) {
            MoviesScreen(onMovieClick = { movieId -> navController.navigate(NavItem.DETAIL.route + "/$movieId")})
        }
        composable(
            route = "${NavItem.DETAIL.route}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: -1

            WorkerDetails(onCloseClick = { navController.navigate(NavItem.MOVIES.route)}, movieId = movieId)
        }
        composable(
            route = "${NavItem.ERROR.route}/{origin}",
            arguments = listOf(navArgument("origin") { type = NavType.StringType })
        ) { backStackEntry ->
            val origin = backStackEntry.arguments?.getString("origin")
            ErrorScreen(onRetryClicked = { if(origin == "details"){ navController.navigate(NavItem.MOVIES.route)} else { navController.navigate(NavItem.DETAIL.route)} }, errorMessage = "Error happened")
        }
    }
}