package com.raflisalam.moviesapp.core.common.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object MoviesIdStateFlow {

    private val currentIdMovies = MutableStateFlow(0)
    fun getCurrentIdMovies(): StateFlow<Int> = currentIdMovies
    fun onMoviesSelected(moviesId: Int) {
        currentIdMovies.value = moviesId
    }
}