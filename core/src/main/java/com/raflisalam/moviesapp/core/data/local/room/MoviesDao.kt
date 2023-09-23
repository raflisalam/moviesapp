package com.raflisalam.moviesapp.core.data.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raflisalam.moviesapp.core.data.local.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMovie(movie: MoviesEntity)

    @Delete
    suspend fun removeFavoriteMovie(movie: MoviesEntity)

    @Query("DELETE FROM movies WHERE id = :movieId")
    suspend fun deleteFavoriteMovies(movieId: Int)

    @Query("SELECT * FROM movies")
    fun getFavoriteMovies(): Flow<List<MoviesEntity>>
}