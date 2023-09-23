package com.raflisalam.moviesapp.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.moviesapp.core.domain.model.FavoriteMovies
import com.raflisalam.moviesapp.core.domain.usecase.favorite.FavoriteMoviesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoriteViewModel(
   private val useCase: FavoriteMoviesUseCase
): ViewModel() {

    fun deleteFavoriteMovieById(movieId: Int) {
        viewModelScope.launch {
            useCase.deleteFavoriteMovieById(movieId)
        }
    }

    val getFavoriteMovies: Flow<List<FavoriteMovies>> = useCase.getFavoriteMovies()

}