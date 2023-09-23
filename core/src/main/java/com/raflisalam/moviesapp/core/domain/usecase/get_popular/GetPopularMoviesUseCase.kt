package com.raflisalam.moviesapp.core.domain.usecase.get_popular

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface GetPopularMoviesUseCase {

    suspend operator fun invoke(): Flow<Resource<List<Movies>>>
}