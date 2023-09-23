package com.raflisalam.moviesapp.core.data.remote.response

data class SearchResponse(
    val results: List<Result>?
)

data class Result(
    val id: Int?,
    val title: String?,
    val overview: String?,
    val genre_ids: List<Int>?,
    val poster_path: String?,
    val release_date: String?,
    val vote_average: Double?,
)
