package com.iryna.moviesapp.domain.entity.workersentity

data class MoviesListEntity(
    val page: Int?,
    val movies: List<MovieEntity>?
)

data class MovieEntity(
    val adult: Boolean?,
    val backdropPath: String?,
    val id: Long?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)