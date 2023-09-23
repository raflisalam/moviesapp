package com.raflisalam.moviesapp.core.common.helper

import com.raflisalam.moviesapp.core.common.enums.GenresList
import kotlin.math.roundToLong

object Convert {
    fun roundDouble(double: Double): Double {
        return (double * 10.0).roundToLong() / 10.0
    }

    fun toGenres(int: Int): String {
        return when (int) {
            28 -> GenresList.ACTION.value.name
            12 -> GenresList.ADVENTURE.value.name
            16 -> GenresList.ANIMATION.value.name
            35 -> GenresList.COMEDY.value.name
            80 -> GenresList.CRIME.value.name
            99 -> GenresList.DOCUMENTARY.value.name
            18 -> GenresList.DRAMA.value.name
            10751 -> GenresList.FAMILY.value.name
            14 -> GenresList.FANTASY.value.name
            36 -> GenresList.HISTORY.value.name
            27 -> GenresList.HORROR.value.name
            10402 -> GenresList.MUSIC.value.name
            9648 -> GenresList.MYSTERY.value.name
            10749 -> GenresList.ROMANCE.value.name
            878 -> GenresList.SCIENCE_FICTION.value.name
            10770 -> GenresList.TV_MOVIE.value.name
            53 -> GenresList.THRILLER.value.name
            10752 -> GenresList.WAR.value.name
            37 -> GenresList.WESTERN.value.name
            10759 -> GenresList.ACTION_ADVENTURE.value.name
            10762 -> GenresList.KIDS.value.name
            10763 -> GenresList.NEWS.value.name
            10764 -> GenresList.REALITY.value.name
            10765 -> GenresList.SCI_FI_FANTASY.value.name
            10766 -> GenresList.SOAP.value.name
            10767 -> GenresList.TALK.value.name
            10768 -> GenresList.WAR_POLITICS.value.name
            else -> "Id genres Not Found"
        }
    }
}