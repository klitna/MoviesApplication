package com.iryna.moviesapp.presentation.movies

import com.iryna.moviesapp.presentation.model.MoviesListViewEntry

data class MoviesState(
    val isLoading: Boolean = false,
    val moviesList: MoviesListViewEntry = MoviesListViewEntry(0, emptyList()),
    val error: String = ""
)
