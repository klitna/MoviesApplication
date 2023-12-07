package com.iryna.moviesapp.data.datasource.dto

import com.iryna.moviesapp.domain.entity.workerdetailedentity.GenresEntity
import com.iryna.moviesapp.domain.entity.workerdetailedentity.MovieDetailedEntity
import com.iryna.moviesapp.domain.entity.workerdetailedentity.ProductionCompaniesEntity
import com.iryna.moviesapp.domain.entity.workerdetailedentity.ProductionCountriesEntity
import com.iryna.moviesapp.domain.entity.workerdetailedentity.SpokenLanguagesEntity
import com.squareup.moshi.Json

data class MovieDetailedDto(
    @field:Json(name = "adult")
    val adult: Boolean,
    @field:Json(name = "backdrop_path")
    val backdrop_path: String,
    @field:Json(name = "budget")
    val budget: Int,
    @field:Json(name = "genres")
    val genres: List<GenresDto>,
    @field:Json(name = "homepage")
    val homepage: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "imdb_id")
    val imdb_id: String,
    @field:Json(name = "original_language")
    val original_language: String,
    @field:Json(name = "original_title")
    val original_title: String,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "popularity")
    val popularity: Double,
    @field:Json(name = "poster_path")
    val poster_path: String,
    @field:Json(name = "production_companies")
    val production_companies: List<ProductionCompaniesDto>,
    @field:Json(name = "production_countries")
    val production_countries: List<ProductionCountriesDto>,
    @field:Json(name = "release_date")
    val release_date: String,
    @field:Json(name = "revenue")
    val revenue: Int,
    @field:Json(name = "runtime")
    val runtime: Int,
    @field:Json(name = "spoken_languages")
    val spoken_languages: List<SpokenLanguagesDto>,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "tagline")
    val tagline: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "video")
    val video: Boolean,
    @field:Json(name = "vote_average")
    val vote_average: Double,
    @field:Json(name = "vote_count")
    val vote_count: Int
)

data class GenresDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "name")
    val name: String
)

fun GenresDto.toDomainModel(): GenresEntity {
    return GenresEntity(
        id = this.id,
        name = this.name
    )
}

fun MovieDetailedDto.toDomainModel(): MovieDetailedEntity {
    return MovieDetailedEntity(
    adult = this.adult,
    backdropPath = this.backdrop_path,
     budget = this.budget,
     genres = this.genres.map { it.toDomainModel() },
     homepage = this.homepage,
     id = this.id,
     imdbId = imdb_id,
     originalLanguage = original_language,
     originalTitle = original_title,
     overview = overview,
     popularity = popularity,
     posterPath = poster_path,
     productionCompanies = this.production_companies.map { it.toDomainModel() },
     productionCountries = this.production_countries.map { it.toDomainModel() },
     releaseDate = this.release_date,
     revenue = this.revenue,
     runtime = this.runtime,
     spokenLanguages = this.spoken_languages.map { it.toDomainModel() },
     status = this.status,
     tagline = this.tagline,
     title = this.title,
     video = this.video,
     voteAverage = this.vote_average,
     voteCount = this.vote_count
    )
}

data class ProductionCompaniesDto(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "logo_path")
    val logo_path: String?,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "origin_country")
    val origin_country: String
)

fun ProductionCompaniesDto.toDomainModel(): ProductionCompaniesEntity {
    return ProductionCompaniesEntity(
        id = this.id,
        logoPath = this.logo_path,
        name = this.name,
        originCountry = this.origin_country
    )
}

data class ProductionCountriesDto(
    @field:Json(name = "iso_3166_1")
    val iso_3166_1: String,
    @field:Json(name = "name")
    val name: String
)

fun ProductionCountriesDto.toDomainModel(): ProductionCountriesEntity {
    return ProductionCountriesEntity(
        iso_3166_1 = this.iso_3166_1,
        name = this.name
    )
}

data class SpokenLanguagesDto(
    @field:Json(name = "english_name")
    val english_name: String,
    @field:Json(name = "iso_639_1")
    val iso_639_1: String,
    @field:Json(name = "name")
    val name: String
)

fun SpokenLanguagesDto.toDomainModel(): SpokenLanguagesEntity {
    return SpokenLanguagesEntity(
        englishName = this.english_name,
        iso_639_1 = this.iso_639_1,
        name = this.name
    )
}
