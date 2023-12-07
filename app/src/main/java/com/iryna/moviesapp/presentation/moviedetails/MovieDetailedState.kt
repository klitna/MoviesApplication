package com.iryna.moviesapp.presentation.moviedetails

import com.iryna.moviesapp.presentation.model.MovieDetailedViewEntry

data class MovieDetailedState(
    val isLoading: Boolean = false,
    val movieDetailed: MovieDetailedViewEntry? = null,
    val error: String = ""
)