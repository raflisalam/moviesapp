package com.raflisalam.moviesapp.core.domain.usecase.search

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface SearchMoviesUseCase {

    suspend operator fun invoke(moviesName: String): Flow<Resource<List<Movies>>>
}