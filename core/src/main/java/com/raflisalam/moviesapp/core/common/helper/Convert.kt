package com.raflisalam.moviesapp.core.common.helper

import com.raflisalam.moviesapp.core.common.enums.GenresList

object Convert {
    fun roundDouble(double: Double): Double {
        return Math.round(double * 10.0) / 10.0
    }

    fun genresListIdToListString(listId: List<Int>): List<String> {
        val genreName = listId.mapNotNull { id ->
            com.raflisalam.moviesapp.core.common.enums.GenresList.values().find { it.value.id == id }?.value?.name
        }
        return genreName
    }

    fun genresToInt(string: String): Int {
        return when(string) {
            "Action" -> com.raflisalam.moviesapp.core.common.enums.GenresList.ACTION.value.id
            "Adventure" -> com.raflisalam.moviesapp.core.common.enums.GenresList.ADVENTURE.value.id
            "Animation" -> com.raflisalam.moviesapp.core.common.enums.GenresList.ANIMATION.value.id
            "Comedy" -> com.raflisalam.moviesapp.core.common.enums.GenresList.COMEDY.value.id
            "Crime" -> com.raflisalam.moviesapp.core.common.enums.GenresList.CRIME.value.id
            "Documentary" -> com.raflisalam.moviesapp.core.common.enums.GenresList.DOCUMENTARY.value.id
            "Drama" -> com.raflisalam.moviesapp.core.common.enums.GenresList.DRAMA.value.id
            "Family" -> com.raflisalam.moviesapp.core.common.enums.GenresList.FAMILY.value.id
            "Fantasy" -> com.raflisalam.moviesapp.core.common.enums.GenresList.FANTASY.value.id
            "History" -> com.raflisalam.moviesapp.core.common.enums.GenresList.HISTORY.value.id
            "Horror" -> com.raflisalam.moviesapp.core.common.enums.GenresList.HORROR.value.id
            "Music" -> com.raflisalam.moviesapp.core.common.enums.GenresList.MUSIC.value.id
            "Mystery" -> com.raflisalam.moviesapp.core.common.enums.GenresList.MYSTERY.value.id
            "Romance" -> com.raflisalam.moviesapp.core.common.enums.GenresList.ROMANCE.value.id
            "Science Fiction" -> com.raflisalam.moviesapp.core.common.enums.GenresList.SCIENCE_FICTION.value.id
            "TV Movie" -> com.raflisalam.moviesapp.core.common.enums.GenresList.TV_MOVIE.value.id
            "Thriller" -> com.raflisalam.moviesapp.core.common.enums.GenresList.THRILLER.value.id
            "War" -> com.raflisalam.moviesapp.core.common.enums.GenresList.WAR.value.id
            "Western" -> com.raflisalam.moviesapp.core.common.enums.GenresList.WESTERN.value.id
            "Action & Adventure" -> com.raflisalam.moviesapp.core.common.enums.GenresList.ACTION_ADVENTURE.value.id
            "Kids" -> com.raflisalam.moviesapp.core.common.enums.GenresList.KIDS.value.id
            "News" -> com.raflisalam.moviesapp.core.common.enums.GenresList.NEWS.value.id
            "Reality" -> com.raflisalam.moviesapp.core.common.enums.GenresList.REALITY.value.id
            "Sci-Fi & Fantasy" -> com.raflisalam.moviesapp.core.common.enums.GenresList.SCI_FI_FANTASY.value.id
            "Soap" -> com.raflisalam.moviesapp.core.common.enums.GenresList.SOAP.value.id
            "Talk" -> com.raflisalam.moviesapp.core.common.enums.GenresList.TALK.value.id
            "War & Politics" -> com.raflisalam.moviesapp.core.common.enums.GenresList.WAR_POLITICS.value.id
            else -> 0
        }
    }

    fun toGenres(int: Int): String {
        return when (int) {
            28 -> com.raflisalam.moviesapp.core.common.enums.GenresList.ACTION.value.name
            12 -> com.raflisalam.moviesapp.core.common.enums.GenresList.ADVENTURE.value.name
            16 -> com.raflisalam.moviesapp.core.common.enums.GenresList.ANIMATION.value.name
            35 -> com.raflisalam.moviesapp.core.common.enums.GenresList.COMEDY.value.name
            80 -> com.raflisalam.moviesapp.core.common.enums.GenresList.CRIME.value.name
            99 -> com.raflisalam.moviesapp.core.common.enums.GenresList.DOCUMENTARY.value.name
            18 -> com.raflisalam.moviesapp.core.common.enums.GenresList.DRAMA.value.name
            10751 -> com.raflisalam.moviesapp.core.common.enums.GenresList.FAMILY.value.name
            14 -> com.raflisalam.moviesapp.core.common.enums.GenresList.FANTASY.value.name
            36 -> com.raflisalam.moviesapp.core.common.enums.GenresList.HISTORY.value.name
            27 -> com.raflisalam.moviesapp.core.common.enums.GenresList.HORROR.value.name
            10402 -> com.raflisalam.moviesapp.core.common.enums.GenresList.MUSIC.value.name
            9648 -> com.raflisalam.moviesapp.core.common.enums.GenresList.MYSTERY.value.name
            10749 -> com.raflisalam.moviesapp.core.common.enums.GenresList.ROMANCE.value.name
            878 -> com.raflisalam.moviesapp.core.common.enums.GenresList.SCIENCE_FICTION.value.name
            10770 -> com.raflisalam.moviesapp.core.common.enums.GenresList.TV_MOVIE.value.name
            53 -> com.raflisalam.moviesapp.core.common.enums.GenresList.THRILLER.value.name
            10752 -> com.raflisalam.moviesapp.core.common.enums.GenresList.WAR.value.name
            37 -> com.raflisalam.moviesapp.core.common.enums.GenresList.WESTERN.value.name
            10759 -> com.raflisalam.moviesapp.core.common.enums.GenresList.ACTION_ADVENTURE.value.name
            10762 -> com.raflisalam.moviesapp.core.common.enums.GenresList.KIDS.value.name
            10763 -> com.raflisalam.moviesapp.core.common.enums.GenresList.NEWS.value.name
            10764 -> com.raflisalam.moviesapp.core.common.enums.GenresList.REALITY.value.name
            10765 -> com.raflisalam.moviesapp.core.common.enums.GenresList.SCI_FI_FANTASY.value.name
            10766 -> com.raflisalam.moviesapp.core.common.enums.GenresList.SOAP.value.name
            10767 -> com.raflisalam.moviesapp.core.common.enums.GenresList.TALK.value.name
            10768 -> com.raflisalam.moviesapp.core.common.enums.GenresList.WAR_POLITICS.value.name
            else -> "Id genres Not Found"
        }
    }
}