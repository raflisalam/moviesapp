package com.raflisalam.moviesapp.core.domain.usecase.get_toprated

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow


interface GetTopRatedMoviesUseCase {
    suspend operator fun invoke(): Flow<Resource<List<Movies>>>
}