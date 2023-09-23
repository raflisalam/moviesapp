package com.raflisalam.moviesapp.core.domain.repository

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.data.remote.response.detail.MovieDetailsDto
import com.raflisalam.moviesapp.core.domain.model.CastMovies
import com.raflisalam.moviesapp.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getPopularMovies(): Flow<Resource<List<Movies>>>
    suspend fun getTopRatedMovies(): Flow<Resource<List<Movies>>>
    suspend fun getDetailsMovieById(movieId: Int): MovieDetailsDto
    suspend fun getRecommendationsMovies(movieId: Int): Flow<Resource<List<Movies>>>
    suspend fun getCreditsActorById(movieId: Int): Flow<Resource<List<CastMovies>>>
    suspend fun getSearchMoviesByName(moviesName: String): Flow<Resource<List<Movies>>>

}