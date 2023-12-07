package com.iryna.moviesapp.instrumentedtests

import androidx.compose.runtime.Composable
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.iryna.moviesapp.MainActivity
import com.iryna.moviesapp.presentation.splash.SplashScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.*
import com.iryna.moviesapp.presentation.movies.MovieListScreen
import com.iryna.moviesapp.presentation.movies.MoviesViewModel
import io.mockk.every
import io.mockk.mockk

@RunWith(AndroidJUnit4::class)
class MoviesAppInstrumentedTests {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        // Initialize any test-specific setup here
    }

    @Test
    fun testSplashScreen() {
        // Check if the splash screen is displayed
        composeTestRule.setContent {
            MyComposableScreen()
        }

        composeTestRule.onNodeWithText("YourSplashScreenText").assertIsDisplayed()
        // Add more assertions as needed
    }

    @Composable
    fun MyComposableScreen() {
        SplashScreen(onAnimationFinished = {  })
    }

    @Test
    fun testMovieListScreen() {
        // Mock ViewModel and provide fake data for testing
        val viewModel = mockk<MoviesViewModel>(relaxed = true)
        every { viewModel.originalMovies } returns fakeMovieList

        // Check if the movie list screen is displayed
        composeTestRule.setContent {
            MovieListScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithText("YourMovieListTitle").assertIsDisplayed()

        // Test filtering
        composeTestRule.onNodeWithText("Filter").performClick()
        composeTestRule.onNodeWithText("YourTitleFilter").performTextInput("FilterText")
        // Apply filter and assert changes

        // Test navigating to detail screen
        composeTestRule.onNodeWithText("YourMovieTitle").performClick()
        composeTestRule.onNodeWithText("YourMovieDetail").assertIsDisplayed()

        // Add more assertions as needed
    }

    @Test
    fun testMovieDetailScreen() {
        // Mock ViewModel and provide fake data for testing
        val viewModel = mockk<MoviesViewModel>(relaxed = true)
        every { viewModel.originalMovies } returns fakeMovieDetail

        // Check if the movie detail screen is displayed
        composeTestRule.setContent {
            MovieDetailScreen(viewModel = viewModel)
        }

        composeTestRule.onNodeWithText("YourMovieDetail").assertIsDisplayed()
        // Add more assertions as needed
    }
}
