package com.raflisalam.moviesapp.core.domain.usecase.favorite

import com.raflisalam.moviesapp.core.domain.model.FavoriteMovies
import kotlinx.coroutines.flow.Flow


interface FavoriteMoviesUseCase {
    suspend operator fun invoke(movies: FavoriteMovies)
    suspend fun deleteFavoriteMovieById(movieId: Int)
    fun getFavoriteMovies(): Flow<List<FavoriteMovies>>
}