package com.raflisalam.moviesapp.core.domain.usecase.get_popular

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.Movies
import com.raflisalam.moviesapp.core.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularMoviesImpl @Inject constructor(
   private val repository: MoviesRepository
): GetPopularMoviesUseCase {

   override suspend fun invoke(): Flow<Resource<List<Movies>>> {
      return repository.getPopularMovies()
   }


}