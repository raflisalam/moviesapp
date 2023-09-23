package com.raflisalam.moviesapp.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MoviesEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterUrl: String,
    val description: String,
    val release_date: String,
    val rating: Double
)