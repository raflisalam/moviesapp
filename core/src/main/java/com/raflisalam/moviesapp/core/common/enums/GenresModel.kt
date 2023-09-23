package com.raflisalam.moviesapp.core.common.enums

data class GenresModel(
    val id: Int,
    val name: String
)

enum class GenresList(val value: com.raflisalam.moviesapp.core.common.enums.GenresModel) {
    ACTION(com.raflisalam.moviesapp.core.common.enums.GenresModel(28, "Action")),
    ACTION_ADVENTURE(
        com.raflisalam.moviesapp.core.common.enums.GenresModel(
            10759,
            "Action & Adventure"
        )
    ),
    ADVENTURE(com.raflisalam.moviesapp.core.common.enums.GenresModel(12, "Adventure")),
    ANIMATION(com.raflisalam.moviesapp.core.common.enums.GenresModel(16, "Animation")),
    COMEDY(com.raflisalam.moviesapp.core.common.enums.GenresModel(35, "Comedy")),
    CRIME(com.raflisalam.moviesapp.core.common.enums.GenresModel(80, "Crime")),
    DOCUMENTARY(com.raflisalam.moviesapp.core.common.enums.GenresModel(99, "Documentary")),
    DRAMA(com.raflisalam.moviesapp.core.common.enums.GenresModel(18, "Drama")),
    FAMILY(com.raflisalam.moviesapp.core.common.enums.GenresModel(10751, "Family")),
    FANTASY(com.raflisalam.moviesapp.core.common.enums.GenresModel(14, "Fantasy")),
    HISTORY(com.raflisalam.moviesapp.core.common.enums.GenresModel(36, "History")),
    HORROR(com.raflisalam.moviesapp.core.common.enums.GenresModel(27, "Horror")),
    KIDS(com.raflisalam.moviesapp.core.common.enums.GenresModel(10762, "Kids")),
    MUSIC(com.raflisalam.moviesapp.core.common.enums.GenresModel(10402, "Music")),
    MYSTERY(com.raflisalam.moviesapp.core.common.enums.GenresModel(9648, "Mystery")),
    NEWS(com.raflisalam.moviesapp.core.common.enums.GenresModel(10763, "News")),
    REALITY(com.raflisalam.moviesapp.core.common.enums.GenresModel(10764, "Reality")),
    ROMANCE(com.raflisalam.moviesapp.core.common.enums.GenresModel(10749, "Romance")),
    SCIENCE_FICTION(com.raflisalam.moviesapp.core.common.enums.GenresModel(878, "Science Fiction")),
    SCI_FI_FANTASY(
        com.raflisalam.moviesapp.core.common.enums.GenresModel(
            10765,
            "Sci-Fi & Fantasy"
        )
    ),
    SOAP(com.raflisalam.moviesapp.core.common.enums.GenresModel(10766, "Soap")),
    TALK(com.raflisalam.moviesapp.core.common.enums.GenresModel(10767, "Talk")),
    TV_MOVIE(com.raflisalam.moviesapp.core.common.enums.GenresModel(10770, "TV Movie")),
    THRILLER(com.raflisalam.moviesapp.core.common.enums.GenresModel(53, "Thriller")),
    WAR(com.raflisalam.moviesapp.core.common.enums.GenresModel(10752, "War")),
    WAR_POLITICS(com.raflisalam.moviesapp.core.common.enums.GenresModel(10768, "War & Politics")),
    WESTERN(com.raflisalam.moviesapp.core.common.enums.GenresModel(37, "Western"))

}

