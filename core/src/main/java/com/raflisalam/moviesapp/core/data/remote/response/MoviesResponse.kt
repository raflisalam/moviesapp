package com.raflisalam.moviesapp.core.data.remote.response

data class MoviesResponse(
    val id: Int?,
    val genre_ids: List<Int>,
    val overview: String?,
    val release_date: String?,
    val backdrop_path: String?,
    val title: String?,
    val poster_path: String?,
    val vote_average: Double?
)