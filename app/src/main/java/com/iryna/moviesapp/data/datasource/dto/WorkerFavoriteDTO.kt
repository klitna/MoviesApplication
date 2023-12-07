package com.iryna.moviesapp.data.datasource.dto

import com.squareup.moshi.Json

data class WorkerFavoriteDto(
    @field:Json(name = "color")
    val color: String?,
    @field:Json(name = "food")
    val food: String?,
    @field:Json(name = "random_string")
    val random_string: String?,
    @field:Json(name = "song")
    val song: String?
)