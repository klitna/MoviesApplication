package com.iryna.moviesapp.presentation.movies

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iryna.moviesapp.domain.entity.workersentity.MoviesListEntity
import com.iryna.moviesapp.domain.entity.workersentity.MoviesRequest
import com.iryna.moviesapp.domain.usecases.moviesusecase.MoviesUseCase
import com.iryna.moviesapp.presentation.model.MoviesViewEntry
import com.iryna.moviesapp.presentation.model.MoviesListViewEntry
import com.iryna.moviesapp.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase
): ViewModel() {

    private val _state = MutableStateFlow(MoviesState())
    val state = _state.asStateFlow()

    var titleFilter by mutableStateOf("")
    var yearFilter by mutableStateOf("")
    var isYearAscending by mutableStateOf(true)
    var isTitleAscending by mutableStateOf(true)

    var originalMovies: List<MoviesViewEntry>? = emptyList()

    var page = 1
    fun getMovies() {
        viewModelScope.launch {
            moviesUseCase(MoviesUseCase.Params.getMovies(MoviesRequest(page))).collect {
                when (it) {
                    is ResponseState.Success -> {
                        _state.value = MoviesState(moviesList = MoviesListViewEntry(it.data ?: MoviesListEntity(0, emptyList())))
                        originalMovies = state.value.moviesList.movies
                    }
                    is ResponseState.Loading -> {
                        _state.value = MoviesState(isLoading = true)
                    }
                    is ResponseState.Error -> {
                        _state.value = MoviesState(error = it.message ?: "An unexpected Error")
                    }
                }
            }
        }
    }

    fun filterMovies() {
        _state.value = MoviesState(isLoading = true)

        val filteredMovies = originalMovies?.filter { movie ->
            val isTitleMatched = titleFilter.isEmpty() || movie.title?.contains(titleFilter, ignoreCase = true) == true
            val isYearMatched = yearFilter.isEmpty() || movie.releaseDate?.contains(yearFilter, ignoreCase = true) == true

            isTitleMatched && isYearMatched
        }

        _state.value = MoviesState(moviesList = MoviesListViewEntry(page = state.value.moviesList.page, movies = filteredMovies))
    }
}