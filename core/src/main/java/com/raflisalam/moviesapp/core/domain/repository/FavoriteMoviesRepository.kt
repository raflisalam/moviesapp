package com.raflisalam.moviesapp.core.domain.repository

import com.raflisalam.moviesapp.core.domain.model.FavoriteMovies
import kotlinx.coroutines.flow.Flow

interface FavoriteMoviesRepository {
    suspend fun addFavoriteMovie(movie: FavoriteMovies)
    suspend fun removeFavoriteMovie(movie: FavoriteMovies)
    suspend fun deleteFavoriteMovieById(movieId: Int)
    fun getFavoriteMovies(): Flow<List<FavoriteMovies>>
    suspend fun isMovieWatchlist(movieId: Int): Boolean
}
