package com.iryna.moviesapp.data.datasource.dto

import com.iryna.moviesapp.domain.entity.workersentity.MovieEntity
import com.iryna.moviesapp.domain.entity.workersentity.MoviesListEntity
import com.squareup.moshi.Json

data class ApiResponse(
    @field:Json(name = "page")
    val page: Int?,
    @field:Json(name = "results")
    val results: List<MovieDto>?
)

fun ApiResponse.toDomainModel(): MoviesListEntity {
    return MoviesListEntity(
        page = this.page,
        movies = this.results?.map { it.toDomainModel() } ?: emptyList()
    )
}

data class MovieDto(
    @field:Json(name = "adult")
    val adult: Boolean?,
    @field:Json(name = "backdrop_path")
    val backdrop_path: String?,
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "original_language")
    val original_language: String?,
    @field:Json(name = "original_title")
    val original_title: String?,
    @field:Json(name = "overview")
    val overview: String?,
    @field:Json(name = "popularity")
    val popularity: Double?,
    @field:Json(name = "poster_path")
    val poster_path: String?,
    @field:Json(name = "release_date")
    val release_date: String?,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "video")
    val video: Boolean?,
    @field:Json(name = "vote_average")
    val vote_average: Double?,
    @field:Json(name = "vote_count")
    val vote_count: Int?
)

fun MovieDto.toDomainModel(): MovieEntity {
    return MovieEntity(
        adult = this.adult ?: false,
        backdropPath = this.backdrop_path ?: "",
        id = this.id,
        originalLanguage = this.original_language ?: "",
        originalTitle = this.original_title ?: "",
        overview = this.overview ?: "",
        popularity = this.popularity ?: 0.0,
        posterPath = this.poster_path ?: "",
        releaseDate = this.release_date ?: "",
        title = this.title ?: "",
        video = this.video ?: false,
        voteAverage = this.vote_average ?: 0.0,
        voteCount = this.vote_count ?: 0,
    )
}

