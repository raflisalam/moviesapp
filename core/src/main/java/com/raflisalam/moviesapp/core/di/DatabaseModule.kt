package com.raflisalam.moviesapp.core.di

import android.content.Context
import androidx.room.Room
import com.raflisalam.moviesapp.core.common.constant.Constants
import com.raflisalam.moviesapp.core.data.local.room.MoviesDao
import com.raflisalam.moviesapp.core.data.local.room.MoviesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MoviesDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes(Constants.ENCRYPTED_DB_PASSPHRASE.toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(
            context.applicationContext,
            MoviesDatabase::class.java,
            "Movies-app.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.moviesDao()
    }
}