package com.raflisalam.moviesapp.core.domain.usecase.detail.get_recommendations

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.Movies
import com.raflisalam.moviesapp.core.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendationsMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetRecommendationsMoviesUseCase {
    override suspend fun invoke(movieId: Int): Flow<Resource<List<Movies>>> {
        return repository.getRecommendationsMovies(movieId)
    }
}