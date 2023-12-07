package com.iryna.moviesapp.domain.repository

import com.iryna.moviesapp.data.datasource.dto.ApiResponse
import com.iryna.moviesapp.data.datasource.dto.MovieDetailedDto
import com.iryna.moviesapp.domain.entity.workerdetailedentity.MovieDetailedRequestEntity
import com.iryna.moviesapp.domain.entity.workersentity.MoviesRequest

interface IMoviesRepository {
    suspend fun getMovies(movies: MoviesRequest): ApiResponse

    suspend fun getMovieDetailed(id: Int): MovieDetailedDto
}