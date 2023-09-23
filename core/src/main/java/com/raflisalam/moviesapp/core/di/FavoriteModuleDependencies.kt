package com.raflisalam.moviesapp.core.di

import com.raflisalam.moviesapp.core.domain.usecase.favorite.FavoriteMoviesUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {
    fun favoriteUseCase(): FavoriteMoviesUseCase
}