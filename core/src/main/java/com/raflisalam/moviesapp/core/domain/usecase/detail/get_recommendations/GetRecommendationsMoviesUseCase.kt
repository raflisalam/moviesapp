package com.raflisalam.moviesapp.core.domain.usecase.detail.get_recommendations

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface GetRecommendationsMoviesUseCase {
    suspend operator fun invoke(movieId: Int): Flow<Resource<List<Movies>>>
}