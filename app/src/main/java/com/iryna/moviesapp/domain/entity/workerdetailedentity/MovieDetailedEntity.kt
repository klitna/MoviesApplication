package com.iryna.moviesapp.domain.entity.workerdetailedentity

data class MovieDetailedEntity(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genres: List<GenresEntity>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompaniesEntity>,
    val productionCountries: List<ProductionCountriesEntity>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguagesEntity>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

data class GenresEntity(
    val id: Int,
    val name: String
)

data class SpokenLanguagesEntity(
    val englishName: String,
    val iso_639_1: String,
    val name: String
)

data class ProductionCountriesEntity(
    val iso_3166_1: String,
    val name: String
)

data class ProductionCompaniesEntity(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
)
