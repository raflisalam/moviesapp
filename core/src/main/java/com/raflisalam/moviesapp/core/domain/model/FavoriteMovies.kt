package com.raflisalam.moviesapp.core.domain.model

data class FavoriteMovies(
    val id: Int,
    val title: String,
    val image_poster: String,
    val description: String,
    val release_date: String,
    val rating: Double
)