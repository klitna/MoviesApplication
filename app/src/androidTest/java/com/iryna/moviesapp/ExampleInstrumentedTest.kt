package com.iryna.moviesapp

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.iryna.moviesapp.presentation.splash.SplashScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.iryna.moviesapp.presentation.model.MoviesViewEntry
import com.iryna.moviesapp.presentation.movies.MovieListScreen
import com.iryna.moviesapp.presentation.movies.MoviesViewModel
import io.mockk.every
import io.mockk.mockk

/*@RunWith(AndroidJUnit4::class)
class MoviesAppInstrumentedTests {

    private lateinit var fakeMovieDetail: List<MoviesViewEntry>

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        fakeMovieDetail = createFakeMovieEntries(2)
    }

    @Test
    fun testSplashScreen() {
        composeTestRule.setContent {
            MyComposableScreen()
        }

        composeTestRule.onNodeWithText("YourSplashScreenText").assertIsDisplayed()
    }

    @Composable
    fun MyComposableScreen() {
        SplashScreen(onAnimationFinished = {  })
    }

    @Test
    fun testMovieListScreen() {
        val viewModel = mockk<MovieViewModel>(relaxed = true)
        every { viewModel.originalMovies } returns fakeMovieDetail

        composeTestRule.setContent {
            MovieDetails(
                viewModel = viewModel,
                movieId = 123,
                onCloseClick = { /* Provide a valid onCloseClick action */ }
            )
        }

        composeTestRule.onNodeWithText("YourMovieListTitle").assertIsDisplayed()

        composeTestRule.onNodeWithText("Filter").performClick()
        composeTestRule.onNodeWithText("YourTitleFilter").performTextInput("FilterText")

        composeTestRule.onNodeWithText("movieTitle").performClick()
        composeTestRule.onNodeWithText("YourMovieDetail").assertIsDisplayed()

        // Add more assertions as needed
    }

    @Test
    fun testMovieDetailScreen() {
        val viewModel = mockk<MoviesViewModel>(relaxed = true)
        every { viewModel.originalMovies } returns fakeMovieDetail

        // Check if the movie detail screen is displayed
        composeTestRule.setContent {
            MovieDetails(
                viewModel = viewModel,
                movieId = 123, // Provide a valid movieId value
                onCloseClick = { /* Provide a valid onCloseClick action */ }
            )
        }

        composeTestRule.onNodeWithText("YourMovieDetail").assertIsDisplayed()
        // Add more assertions as needed
    }

    fun createFakeMovieEntries(count: Int): List<MoviesViewEntry> {
        val fakeMovieEntries = mutableListOf<MoviesViewEntry>()

        repeat(count) {
            val fakeMovieEntry = MoviesViewEntry(
                adult = false,
                backdropPath = "/fake_backdrop_path_$it",
                id = it.toLong(),
                originalLanguage = "en",
                originalTitle = "Fake Movie $it",
                overview = "This is the overview for Fake Movie $it",
                popularity = 7.5,
                posterPath = "/fake_poster_path_$it",
                releaseDate = "2023-01-01",
                title = "Fake Movie $it",
                video = false,
                voteAverage = 7.0,
                voteCount = 100
            )
            fakeMovieEntries.add(fakeMovieEntry)
        }

        return fakeMovieEntries
    }


}
*/