package com.iryna.moviesapp.presentation.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iryna.moviesapp.domain.entity.workerdetailedentity.MovieDetailedRequestEntity
import com.iryna.moviesapp.domain.usecases.moviedetailedusecase.MovieDetailedUseCase
import com.iryna.moviesapp.presentation.model.MovieDetailedViewEntry
import com.iryna.moviesapp.util.ResponseState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailedViewModel @Inject constructor(
    private val movieDetailedUseCase: MovieDetailedUseCase
): ViewModel() {

    private val _state = MutableStateFlow(MovieDetailedState())
    val state = _state.asStateFlow()

    var movieId = 1

    fun getWorkerDetailed(id: Int) {
        viewModelScope.launch {
            movieDetailedUseCase(
                MovieDetailedUseCase.Params.getMovieDetailed(
                MovieDetailedRequestEntity(id)
            )).collect {
                when (it) {
                    is ResponseState.Success -> {
                        _state.value = MovieDetailedState(movieDetailed = MovieDetailedViewEntry(it.data))
                    }
                    is ResponseState.Loading -> {
                        _state.value = MovieDetailedState(isLoading = true)
                    }
                    is ResponseState.Error -> {
                        _state.value = MovieDetailedState(error = it.message ?: "An unexpected Error")
                    }
                }
            }
        }
    }
}