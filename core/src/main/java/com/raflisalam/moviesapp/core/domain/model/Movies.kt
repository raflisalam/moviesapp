package com.raflisalam.moviesapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val id: Int,
    val genreId: List<Int>,
    val title: String,
    val poster: String,
    val background: String,
    val description: String,
    val release_date: String,
    val rating: Double
): Parcelable
