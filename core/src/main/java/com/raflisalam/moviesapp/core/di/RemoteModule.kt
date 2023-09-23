package com.raflisalam.moviesapp.core.di

import com.raflisalam.moviesapp.core.data.local.room.MoviesDao
import com.raflisalam.moviesapp.core.data.remote.network.MoviesApi
import com.raflisalam.moviesapp.core.data.local.repository.FavoriteMoviesRepositoryImpl
import com.raflisalam.moviesapp.core.data.remote.repository.MoviesRepositoryImpl
import com.raflisalam.moviesapp.core.domain.repository.FavoriteMoviesRepository
import com.raflisalam.moviesapp.core.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
       apiServices: MoviesApi
    ): MoviesRepository {
        return MoviesRepositoryImpl(apiServices)
    }

    @Provides
    @Singleton
    fun provideFavoriteMoviesRepository(
        dao: MoviesDao
    ): FavoriteMoviesRepository {
        return FavoriteMoviesRepositoryImpl(dao)
    }
}