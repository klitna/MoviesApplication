package com.iryna.moviesapp.data.repository

import javax.inject.Inject
import com.iryna.moviesapp.data.datasource.MoviesApi
import com.iryna.moviesapp.data.datasource.dto.ApiResponse
import com.iryna.moviesapp.data.datasource.dto.MovieDetailedDto
import com.iryna.moviesapp.domain.entity.workersentity.MoviesRequest
import com.iryna.moviesapp.domain.repository.IMoviesRepository

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
    ): IMoviesRepository {
    override suspend fun getMovies(moviesRequest: MoviesRequest): ApiResponse {
        return moviesApi.getMovies(moviesRequest.page)
    }

    override suspend fun getMovieDetailed(id: Int): MovieDetailedDto {
        return moviesApi.getDetailedMovie(id)
    }
}