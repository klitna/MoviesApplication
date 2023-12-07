package com.iryna.moviesapp.presentation.model

import android.os.Parcelable
import com.iryna.moviesapp.domain.entity.workerdetailedentity.GenresEntity
import com.iryna.moviesapp.domain.entity.workerdetailedentity.MovieDetailedEntity
import com.iryna.moviesapp.domain.entity.workerdetailedentity.ProductionCompaniesEntity
import com.iryna.moviesapp.domain.entity.workerdetailedentity.ProductionCountriesEntity
import com.iryna.moviesapp.domain.entity.workerdetailedentity.SpokenLanguagesEntity
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetailedViewEntry(
    val adult: Boolean,
    val backdropPath: String,
    val budget: Int,
    val genres: List<GenresViewEntry>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompaniesViewEntry>,
    val productionCountries: List<ProductionCountriesViewEntry>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguagesViewEntry>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) : Parcelable {
    fun toEntity(): MovieDetailedEntity {
        return MovieDetailedEntity(
            adult = adult,
            backdropPath = backdropPath,
            budget = budget,
            genres = genres.map { it.toEntity() },
            homepage = homepage,
            id = id,
            imdbId = imdbId,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            productionCompanies = productionCompanies.map { it.toEntity() },
            productionCountries = productionCountries.map { it.toEntity() },
            releaseDate = releaseDate,
            revenue = revenue,
            runtime = runtime,
            spokenLanguages = spokenLanguages.map { it.toEntity() },
            status = status,
            tagline = tagline,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }

    constructor(movieDetailedEntity: MovieDetailedEntity?) : this(
        adult = movieDetailedEntity?.adult ?: false,
        backdropPath = movieDetailedEntity?.backdropPath ?: "",
        budget = movieDetailedEntity?.budget ?: 0,
        genres = movieDetailedEntity?.genres?.map { GenresViewEntry(it) } ?: emptyList(),
        homepage = movieDetailedEntity?.homepage ?: "",
        id = movieDetailedEntity?.id ?: 0,
        imdbId = movieDetailedEntity?.imdbId ?: "",
        originalLanguage = movieDetailedEntity?.originalLanguage ?: "",
        originalTitle = movieDetailedEntity?.originalTitle ?: "",
        overview = movieDetailedEntity?.overview ?: "",
        popularity = movieDetailedEntity?.popularity ?: 0.0,
        posterPath = movieDetailedEntity?.posterPath ?: "",
        productionCompanies = movieDetailedEntity?.productionCompanies?.map { ProductionCompaniesViewEntry(it) }
            ?: emptyList(),
        productionCountries = movieDetailedEntity?.productionCountries?.map { ProductionCountriesViewEntry(it) }
            ?: emptyList(),
        releaseDate = movieDetailedEntity?.releaseDate ?: "",
        revenue = movieDetailedEntity?.revenue ?: 0,
        runtime = movieDetailedEntity?.runtime ?: 0,
        spokenLanguages = movieDetailedEntity?.spokenLanguages?.map { SpokenLanguagesViewEntry(it) }
            ?: emptyList(),
        status = movieDetailedEntity?.status ?: "",
        tagline = movieDetailedEntity?.tagline ?: "",
        title = movieDetailedEntity?.title ?: "",
        video = movieDetailedEntity?.video ?: false,
        voteAverage = movieDetailedEntity?.voteAverage ?: 0.0,
        voteCount = movieDetailedEntity?.voteCount ?: 0
    )
}

@Parcelize
data class GenresViewEntry(
    val id: Int,
    val name: String
) : Parcelable {
    fun toEntity(): GenresEntity {
        return GenresEntity(
            id = id,
            name = name
        )
    }

    constructor(genresEntity: GenresEntity?) : this(
        id = genresEntity?.id ?: 0,
        name = genresEntity?.name ?: ""
    )
}

@Parcelize
data class ProductionCompaniesViewEntry(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
) : Parcelable {
    fun toEntity(): ProductionCompaniesEntity {
        return ProductionCompaniesEntity(
            id = id,
            logoPath = logoPath,
            name = name,
            originCountry = originCountry
        )
    }

    constructor(productionCompaniesEntity: ProductionCompaniesEntity?) : this(
        id = productionCompaniesEntity?.id ?: 0,
        logoPath = productionCompaniesEntity?.logoPath,
        name = productionCompaniesEntity?.name ?: "",
        originCountry = productionCompaniesEntity?.originCountry ?: ""
    )
}

@Parcelize
data class ProductionCountriesViewEntry(
    val iso_3166_1: String,
    val name: String
) : Parcelable {
    fun toEntity(): ProductionCountriesEntity {
        return ProductionCountriesEntity(
            iso_3166_1 = iso_3166_1,
            name = name
        )
    }

    constructor(productionCountriesEntity: ProductionCountriesEntity?) : this(
        iso_3166_1 = productionCountriesEntity?.iso_3166_1 ?: "",
        name = productionCountriesEntity?.name ?: ""
    )
}

@Parcelize
data class SpokenLanguagesViewEntry(
    val englishName: String,
    val iso_639_1: String,
    val name: String
) : Parcelable {
    fun toEntity(): SpokenLanguagesEntity {
        return SpokenLanguagesEntity(
            englishName = englishName,
            iso_639_1 = iso_639_1,
            name = name
        )
    }

    constructor(spokenLanguagesEntity: SpokenLanguagesEntity?) : this(
        englishName = spokenLanguagesEntity?.englishName ?: "",
        iso_639_1 = spokenLanguagesEntity?.iso_639_1 ?: "",
        name = spokenLanguagesEntity?.name ?: ""
    )
}




