package com.raflisalam.moviesapp.core.data.remote.repository

import com.raflisalam.moviesapp.core.common.utils.mapResponseCreditsCastToDomain
import com.raflisalam.moviesapp.core.common.utils.mapResponsesToDomain
import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.data.remote.network.MoviesApi
import com.raflisalam.moviesapp.core.data.remote.response.detail.MovieDetailsDto
import com.raflisalam.moviesapp.core.domain.model.CastMovies
import com.raflisalam.moviesapp.core.domain.model.Movies
import com.raflisalam.moviesapp.core.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    private val apiServices: MoviesApi
): MoviesRepository {

    override suspend fun getPopularMovies(): Flow<Resource<List<Movies>>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiServices.getPopularMovies()
            if (response.isSuccessful) {
                val movieResponse = response.body()
                val movieList = mapResponsesToDomain(movieResponse)
                emit(Resource.Success(movieList))
            } else {
                emit(Resource.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Resource.Error("An unexpected error occurred"))
        }
    }

    override suspend fun getTopRatedMovies(): Flow<Resource<List<Movies>>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiServices.getTopRatedMovies()
            if (response.isSuccessful) {
                val movieResponse = response.body()
                val movieList = mapResponsesToDomain(movieResponse)
                emit(Resource.Success(movieList))
            } else {
                emit(Resource.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Resource.Error("An unexpected error occurred"))
        }
    }

    override suspend fun getDetailsMovieById(movieId: Int): MovieDetailsDto {
        return apiServices.getDetailsMovieById(movieId)
    }

    override suspend fun getRecommendationsMovies(movieId: Int): Flow<Resource<List<Movies>>> = flow {
        try {
            val response = apiServices.getRecommendationsMovies(movieId)
            if (response.isSuccessful) {
                val movies = response.body()
                val listMovies = mapResponsesToDomain(movies)
                emit(Resource.Success(listMovies))
            } else {
                emit(Resource.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("An unexpected error occurred ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Resource.Error("An unexpected error occurred ${e.localizedMessage}"))
        }
    }

    override suspend fun getCreditsActorById(movieId: Int): Flow<Resource<List<CastMovies>>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiServices.getCreditsMovieById(movieId)
            if (response.isSuccessful) {
                val credits = response.body()
                val listActor = mapResponseCreditsCastToDomain(credits)
                emit(Resource.Success(listActor))
            } else {
                emit(Resource.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error("An unexpected error occurred ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Resource.Error("An unexpected error occurred ${e.localizedMessage}"))
        }
    }

    override suspend fun getSearchMoviesByName(moviesName: String): Flow<Resource<List<Movies>>> = flow {
        try {
            emit(Resource.Loading())
            val response = apiServices.searchMoviesByName(moviesName)
            if (response.isSuccessful) {
                val movieResponse = response.body()
                val movieList = mapResponsesToDomain(movieResponse)
                emit(Resource.Success(movieList))
            } else {
                emit(Resource.Error("API request failed with code ${response.code()}"))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection"))
        }  catch (e: Exception) {
            emit(Resource.Error("An unexpected error occurred"))
        }
    }

}