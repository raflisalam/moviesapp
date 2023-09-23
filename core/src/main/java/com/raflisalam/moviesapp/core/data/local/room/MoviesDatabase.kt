package com.raflisalam.moviesapp.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raflisalam.moviesapp.core.data.local.entity.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

}