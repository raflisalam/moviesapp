package com.raflisalam.moviesapp.core.di

import com.raflisalam.moviesapp.core.domain.usecase.favorite.FavoriteMoviesUseCase
import com.raflisalam.moviesapp.core.domain.usecase.favorite.FavoriteMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideFavoriteUseCase(favoriteMoviesUseCaseImpl: FavoriteMoviesUseCaseImpl) : FavoriteMoviesUseCase
}