package com.raflisalam.moviesapp.core.di

import com.raflisalam.moviesapp.core.domain.usecase.search.SearchMoviesUseCaseImpl
import com.raflisalam.moviesapp.core.domain.repository.MoviesRepository
import com.raflisalam.moviesapp.core.domain.usecase.detail.get_credits_movie.GetCreditsMovieUseCase
import com.raflisalam.moviesapp.core.domain.usecase.detail.get_credits_movie.GetCreditsMovieUseCaseImpl
import com.raflisalam.moviesapp.core.domain.usecase.detail.get_recommendations.GetRecommendationsMoviesUseCase
import com.raflisalam.moviesapp.core.domain.usecase.detail.get_recommendations.GetRecommendationsMoviesUseCaseImpl
import com.raflisalam.moviesapp.core.domain.usecase.get_popular.GetPopularMoviesImpl
import com.raflisalam.moviesapp.core.domain.usecase.get_popular.GetPopularMoviesUseCase
import com.raflisalam.moviesapp.core.domain.usecase.get_toprated.GetTopRatedMoviesUseCase
import com.raflisalam.moviesapp.core.domain.usecase.get_toprated.GetTopRatedMoviesUseCaseImpl
import com.raflisalam.moviesapp.core.domain.usecase.search.SearchMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {
    @Provides
    fun provideMoviesUseCase(
        repository: MoviesRepository
    ): GetPopularMoviesUseCase {
        return GetPopularMoviesImpl(repository)
    }

    @Provides
    fun provideGetCreditsMovies(
        repository: MoviesRepository
    ): GetCreditsMovieUseCase {
        return GetCreditsMovieUseCaseImpl(repository)
    }

    @Provides
    fun provideGetRecommendationsMoviesUseCase(
        repository: MoviesRepository
    ): GetRecommendationsMoviesUseCase {
        return GetRecommendationsMoviesUseCaseImpl(repository)
    }

    @Provides
    fun provideSearchMoviesUseCase(
        repository: MoviesRepository
    ): SearchMoviesUseCase {
        return SearchMoviesUseCaseImpl(repository)
    }

    @Provides
    fun provideGetMoviesTopRatedUseCase(
        repository: MoviesRepository
    ): GetTopRatedMoviesUseCase {
        return GetTopRatedMoviesUseCaseImpl(repository)
    }

}