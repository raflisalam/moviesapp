package com.raflisalam.moviesapp.core.data.remote.network

import com.raflisalam.moviesapp.core.data.remote.response.MoviesNetworkResponse
import com.raflisalam.moviesapp.core.data.remote.response.credits.CreditsMovie
import com.raflisalam.moviesapp.core.data.remote.response.detail.MovieDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
    ): Response<MoviesNetworkResponse>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
    ): Response<MoviesNetworkResponse>

    @GET("search/movie")
    suspend fun searchMoviesByName(
        @Query("query") moviesName: String
    ): Response<MoviesNetworkResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetailsMovieById(
        @Path("movie_id") moviesId: Int
    ): MovieDetailsDto

    @GET("movie/{movie_id}/credits")
    suspend fun getCreditsMovieById(
        @Path("movie_id") moviesId: Int
    ): Response<CreditsMovie>

    @GET("movie/{movie_id}/recommendations")
    suspend fun getRecommendationsMovies(
        @Path("movie_id") moviesId: Int
    ): Response<MoviesNetworkResponse>
}