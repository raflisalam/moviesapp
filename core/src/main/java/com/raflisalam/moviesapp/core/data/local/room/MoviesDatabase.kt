package com.raflisalam.moviesapp.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.raflisalam.moviesapp.core.common.utils.ListTypeConverter
import com.raflisalam.moviesapp.core.data.local.entity.MoviesEntity

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
@TypeConverters(ListTypeConverter::class)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao

}