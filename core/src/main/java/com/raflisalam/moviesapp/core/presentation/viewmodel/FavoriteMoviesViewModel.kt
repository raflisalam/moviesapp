package com.raflisalam.moviesapp.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.moviesapp.core.domain.model.FavoriteMovies
import com.raflisalam.moviesapp.core.domain.usecase.favorite.FavoriteMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val useCase: FavoriteMoviesUseCase
): ViewModel() {

    fun toggleFavoriteMovies(movies: FavoriteMovies) {
        viewModelScope.launch {
            useCase.invoke(movies)
        }
    }
    val getFavoriteMovies: Flow<List<FavoriteMovies>> = useCase.getFavoriteMovies()

}