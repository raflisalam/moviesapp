package com.raflisalam.moviesapp.core.common.utils

import com.raflisalam.moviesapp.core.data.local.entity.MoviesEntity
import com.raflisalam.moviesapp.core.data.remote.response.MoviesNetworkResponse
import com.raflisalam.moviesapp.core.data.remote.response.credits.CreditsMovie
import com.raflisalam.moviesapp.core.domain.model.CastMovies
import com.raflisalam.moviesapp.core.domain.model.FavoriteMovies
import com.raflisalam.moviesapp.core.domain.model.Movies

fun mapResponsesToDomain(response: MoviesNetworkResponse?): List<Movies> {
    val movieList = mutableListOf<Movies>()

    response?.results?.map { data ->
        val movie = Movies(
            id = data.id ?: 0,
            genreId = data.genre_ids,
            title = data.title ?: "Unknown movie",
            poster = data.poster_path ?: "",
            background = data.backdrop_path ?: "Unknown Movie",
            description = data.overview ?: "Unknown Description",
            release_date = data.release_date ?: "Unknown Date",
            rating = data.vote_average ?: 0.0
        )
        movieList.add(movie)
    }
    return movieList
}

fun mapResponseCreditsCastToDomain(response: CreditsMovie?): List<CastMovies> {
    val castMoviesList = mutableListOf<CastMovies>()

    response?.cast?.forEach { data ->
        val castMovies = CastMovies(
            id = data.id ?: 0,
            name = data.name ?: "",
            profilePics = data.profile_path ?: "",
            nameCharacter = data.character ?: ""
        )
        castMoviesList.add(castMovies)
    }
    return castMoviesList
}

fun mapEntitiesToDomain(dao: List<MoviesEntity>): List<FavoriteMovies> {
    val favoriteList = mutableListOf<FavoriteMovies>()
    dao.map {
        val favoriteMovies = FavoriteMovies(
            id = it.id,
            title = it.title,
            image_poster = it.posterUrl,
            description = it.description,
            release_date = it.release_date,
            rating = it.rating
        )
        favoriteList.add(favoriteMovies)
    }
    return favoriteList
}

fun mapFavoriteDomainToEntity(data: FavoriteMovies): MoviesEntity {
    return MoviesEntity(
        id = data.id,
        title = data.title,
        posterUrl = data.image_poster,
        description = data.description,
        release_date = data.release_date,
        rating = data.rating
    )
}

