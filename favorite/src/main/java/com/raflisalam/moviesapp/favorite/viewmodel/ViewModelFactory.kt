package com.raflisalam.moviesapp.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raflisalam.moviesapp.core.domain.usecase.favorite.FavoriteMoviesUseCase
import javax.inject.Inject

class ViewModelFactory @Inject constructor(
    private val favoriteUseCase: FavoriteMoviesUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(favoriteUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}