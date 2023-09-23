package com.raflisalam.moviesapp.favorite.di

import android.content.Context
import com.raflisalam.moviesapp.core.di.FavoriteModuleDependencies
import com.raflisalam.moviesapp.favorite.FavoriteMoviesActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {
    fun inject(activity: FavoriteMoviesActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}