package com.iryna.moviesapp.data.datasource

import com.iryna.moviesapp.data.datasource.dto.ApiResponse
import com.iryna.moviesapp.data.datasource.dto.MovieDetailedDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("3/discover/movie")
    suspend fun getMovies(
        @Query("page")
        page: Int
    ): ApiResponse

    @GET("3/movie/{movieId}")
    suspend fun getDetailedMovie(
        @Path("movieId") movieId: Int
    ): MovieDetailedDto
}
