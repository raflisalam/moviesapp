package com.raflisalam.moviesapp.core.domain.usecase.detail.get_credits_movie

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.CastMovies
import com.raflisalam.moviesapp.core.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCreditsMovieUseCaseImpl @Inject constructor(
    private val repository: MoviesRepository
): GetCreditsMovieUseCase {

    override suspend fun invoke(movieId: Int): Flow<Resource<List<CastMovies>>> {
        return repository.getCreditsActorById(movieId)
    }
}