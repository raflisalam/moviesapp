package com.raflisalam.moviesapp.core.data.local.repository

import com.raflisalam.moviesapp.core.common.utils.mapEntitiesToDomain
import com.raflisalam.moviesapp.core.common.utils.mapFavoriteDomainToEntity
import com.raflisalam.moviesapp.core.data.local.room.MoviesDao
import com.raflisalam.moviesapp.core.domain.model.FavoriteMovies
import com.raflisalam.moviesapp.core.domain.repository.FavoriteMoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteMoviesRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao
) : FavoriteMoviesRepository {

    override suspend fun addFavoriteMovie(movie: FavoriteMovies) {
        val entity = mapFavoriteDomainToEntity(movie)
        moviesDao.addFavoriteMovie(entity)
    }

    override suspend fun removeFavoriteMovie(movie: FavoriteMovies) {
        val entity = mapFavoriteDomainToEntity(movie)
        moviesDao.removeFavoriteMovie(entity)
    }

    override suspend fun deleteFavoriteMovieById(movieId: Int) {
        moviesDao.deleteFavoriteMovies(movieId)
    }

    override fun getFavoriteMovies(): Flow<List<FavoriteMovies>> {
        return moviesDao.getFavoriteMovies().map {
            mapEntitiesToDomain(it)
        }
    }

    override suspend fun isMovieWatchlist(movieId: Int): Boolean {
        val favoriteMovies = moviesDao.getFavoriteMovies().first()
        return favoriteMovies.any { it.id == movieId}
    }
}
