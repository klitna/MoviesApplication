package com.iryna.moviesapp.presentation.model

import android.os.Parcelable
import com.iryna.moviesapp.domain.entity.workersentity.MovieEntity
import com.iryna.moviesapp.domain.entity.workersentity.MoviesListEntity
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesListViewEntry(
    val page: Int?,
    val movies: List<MoviesViewEntry>?,
): Parcelable {

    constructor(moviesEntity: MoviesListEntity) : this(
        page = moviesEntity.page,
        movies = moviesEntity.movies?.map { MoviesViewEntry(it) }
    )

    fun toEntity(): MoviesListEntity {
        return MoviesListEntity(
            page = this.page,
            movies = this.movies?.map { it.toEntity() } ?: emptyList()
        )
    }
}

@Parcelize
data class MoviesViewEntry(
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
): Parcelable {
    fun toEntity(): MovieEntity {
        return MovieEntity(
            adult = adult ?: false,
            backdropPath = backdropPath ?: "",
            id = id,
            originalLanguage = originalLanguage ?: "",
            originalTitle = originalTitle ?: "",
            overview = overview ?: "",
            popularity = popularity ?: 0.0,
            posterPath = posterPath ?: "",
            releaseDate = releaseDate ?: "",
            title = title ?: "",
            video = video ?: false,
            voteAverage = voteAverage ?: 0.0,
            voteCount = voteCount ?: 0
        )
    }

    constructor(movieEntity: MovieEntity) : this(
        adult = movieEntity.adult ?: false,
        backdropPath = movieEntity.backdropPath ?: "",
        id = movieEntity.id,
        originalLanguage = movieEntity.originalLanguage ?: "",
        originalTitle = movieEntity.originalTitle ?: "",
        overview = movieEntity.overview ?: "",
        popularity = movieEntity.popularity ?: 0.0,
        posterPath = movieEntity.posterPath ?: "",
        releaseDate = movieEntity.releaseDate ?: "",
        title = movieEntity.title ?: "",
        video = movieEntity.video,
        voteAverage = movieEntity.voteAverage,
        voteCount = movieEntity.voteCount
    )
}
