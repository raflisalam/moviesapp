package com.raflisalam.moviesapp.core.domain.usecase.search

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.Movies
import com.raflisalam.moviesapp.core.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchMoviesUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): SearchMoviesUseCase {

    override suspend fun invoke(moviesName: String): Flow<Resource<List<Movies>>> {
        return repository.getSearchMoviesByName(moviesName)
    }
}