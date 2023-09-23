package com.raflisalam.moviesapp.core.domain.usecase.detail

import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.data.remote.response.detail.toMovieDetails
import com.raflisalam.moviesapp.core.domain.model.MovieDetails
import com.raflisalam.moviesapp.core.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject


class GetDetailsMovieUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieDetails>> = flow {
        try {
            emit(Resource.Loading())
            val movies = repository.getDetailsMovieById(movieId).toMovieDetails()
            emit(Resource.Success(movies))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Resource.Error("An unexpected error occurred"))
        }
    }
}