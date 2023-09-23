package com.raflisalam.moviesapp.core.domain.usecase.get_toprated

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.Movies
import com.raflisalam.moviesapp.core.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetTopRatedMoviesUseCase {
    override suspend fun invoke(): Flow<Resource<List<Movies>>> {
        return repository.getTopRatedMovies()
    }
}