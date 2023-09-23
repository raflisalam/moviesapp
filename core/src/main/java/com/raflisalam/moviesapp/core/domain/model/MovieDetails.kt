package com.raflisalam.moviesapp.core.domain.model

import com.raflisalam.moviesapp.core.data.remote.response.detail.Genre
import com.raflisalam.moviesapp.core.data.remote.response.detail.ProductionCountry

data class MovieDetails(
    val moviesId: Int,
    val backdrop_poster: String,
    val genres: List<Genre>,
    val synopsis: String,
    val image_poster: String,
    val release_date: String,
    val runtime: Int,
    val title: String,
    val rating: Double,
    val rating_vote: Int,
    val productionCountry: List<ProductionCountry>,
    val original_title: String,
    val tagline: String
)

