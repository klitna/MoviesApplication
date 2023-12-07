package com.iryna.moviesapp.domain.usecases

import com.iryna.moviesapp.data.datasource.dto.ApiResponse
import com.iryna.moviesapp.data.datasource.dto.MovieDto
import com.iryna.moviesapp.data.datasource.dto.toDomainModel
import com.iryna.moviesapp.domain.repository.IMoviesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import com.iryna.moviesapp.domain.usecases.moviesusecase.MoviesUseCase
import com.iryna.moviesapp.util.ResponseState
import junit.framework.TestCase.assertEquals

class MoviesUseCaseTest {

    @Test
    fun `should return expected movies from repository`() {
        val expectedMovies = ApiResponse(
            page = 1,
            movies = listOf(
                MovieDto(
                    adult = false,
                    backdrop_path = "/xgGGinKRL8xeRkaAR9RMbtyk60y.jpg",
                    id = 901362,
                    original_language = "en",
                    original_title = "Trolls Band Together",
                    overview = "When Branch's brother, Floyd, is kidnapped for his musical talents by a pair of nefarious pop-star villains, Branch and Poppy embark on a harrowing and emotional journey to reunite the other brothers and rescue Floyd from a fate even worse than pop-culture obscurity.",
                    popularity = 1710.225,
                    poster_path = "/qV4fdXXUm5xNlEJ2jw7af3XxuQB.jpg",
                    release_date = "2023-10-12",
                    title = "Trolls Band Together",
                    video = false,
                    vote_average = 7.2,
                    vote_count = 274
                ),
                MovieDto(
                    adult = false,
                    backdrop_path = "/9PqD3wSIjntyJDBzMNuxuKHwpUD.jpg",
                    id = 1075794,
                    original_language = "en",
                    original_title = "Leo",
                    overview = "Jaded 74-year-old lizard Leo has been stuck in the same Florida classroom for decades with his terrarium-mate turtle. When he learns he only has one year left to live, he plans to escape to experience life on the outside but instead gets caught up in the problems of his anxious students — including an impossibly mean substitute teacher.",
                    popularity = 1508.996,
                    poster_path = "/pD6sL4vntUOXHmuvJPPZAgvyfd9.jpg",
                    release_date = "2023-11-17",
                    title = "Leo",
                    video = false,
                    vote_average = 7.5,
                    vote_count = 416
                )
            )
        )

        val repository: IMoviesRepository = mockk()
        coEvery { repository.getMovies(any()) } returns expectedMovies

        val useCase = MoviesUseCase(repository)

        runBlocking {
            useCase.invoke(MoviesUseCase.Params.getMovies(mockk())).collect {
                response ->
                when (response) {
                    is ResponseState.Success -> {
                        assertEquals(expectedMovies.toDomainModel(), response.data)
                    }
                    is ResponseState.Error -> {
                        println("Test failed with error: ${response.message}")
                        assert(false)
                    }
                    is ResponseState.Loading -> {}
                }
            }
        }
    }
}
