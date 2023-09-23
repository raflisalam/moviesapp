package com.raflisalam.moviesapp.core.domain.usecase.favorite

import com.raflisalam.moviesapp.core.domain.model.FavoriteMovies
import com.raflisalam.moviesapp.core.domain.repository.FavoriteMoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteMoviesUseCaseImpl @Inject constructor(
    private val repository: FavoriteMoviesRepository
) : FavoriteMoviesUseCase {

    override suspend fun invoke(movies: FavoriteMovies) {
        if (repository.isMovieWatchlist(movies.id)) {
            repository.removeFavoriteMovie(movies)
        } else {
            repository.addFavoriteMovie(movies)
        }
    }

    override suspend fun deleteFavoriteMovieById(movieId: Int) {
        repository.deleteFavoriteMovieById(movieId)
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMovies>> {
        return repository.getFavoriteMovies()
    }
}
