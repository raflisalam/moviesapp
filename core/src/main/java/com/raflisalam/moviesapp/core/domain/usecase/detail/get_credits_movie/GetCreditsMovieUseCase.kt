package com.raflisalam.moviesapp.core.domain.usecase.detail.get_credits_movie

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.CastMovies
import kotlinx.coroutines.flow.Flow

interface GetCreditsMovieUseCase {
    suspend operator fun invoke(movieId: Int): Flow<Resource<List<CastMovies>>>
}