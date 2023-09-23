package com.raflisalam.moviesapp.core.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raflisalam.moviesapp.core.common.utils.MoviesIdStateFlow
import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.CastMovies
import com.raflisalam.moviesapp.core.domain.model.MovieDetails
import com.raflisalam.moviesapp.core.domain.model.Movies
import com.raflisalam.moviesapp.core.domain.usecase.detail.GetDetailsMovieUseCase
import com.raflisalam.moviesapp.core.domain.usecase.detail.get_credits_movie.GetCreditsMovieUseCaseImpl
import com.raflisalam.moviesapp.core.domain.usecase.detail.get_recommendations.GetRecommendationsMoviesUseCase
import com.raflisalam.moviesapp.core.domain.usecase.get_popular.GetPopularMoviesUseCase
import com.raflisalam.moviesapp.core.domain.usecase.get_toprated.GetTopRatedMoviesUseCase
import com.raflisalam.moviesapp.core.domain.usecase.search.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getDetailsMovieUseCase: GetDetailsMovieUseCase,
    private val getTopRatedUseCase: GetTopRatedMoviesUseCase,
    private val getCreditsActorMovieUseCase: GetCreditsMovieUseCaseImpl,
    private val recommendationsMovies_useCase: GetRecommendationsMoviesUseCase,
    private val searchMovies_useCase: SearchMoviesUseCase
): ViewModel() {

    private val _getPopularMovies = MutableLiveData<Resource<List<Movies>>>()
    val getPopularMovies: LiveData<Resource<List<Movies>>> get() = _getPopularMovies

    fun fetchPopularMovies() {
        viewModelScope.launch {
            _getPopularMovies.value = Resource.Loading()
            try {
                getPopularMoviesUseCase.invoke().collect {
                    _getPopularMovies.value = it
                }
            } catch (e: Exception) {
                _getPopularMovies.value = Resource.Error("Failed to fetch popular movie list")
            }
        }
    }

    private val _getTopRatedMovies = MutableLiveData<Resource<List<Movies>>>()
    val getTopRatedMovies: LiveData<Resource<List<Movies>>> get() = _getTopRatedMovies

    fun fetchTopRatedMovies() {
        viewModelScope.launch {
            _getTopRatedMovies.value = Resource.Loading()
            try {
                getTopRatedUseCase.invoke().collect {
                    _getTopRatedMovies.value = it
                }
            } catch (e: Exception) {
                _getTopRatedMovies.value = Resource.Error("Failed to fetch now playing movie list")
            }
        }
    }

    private val moviesIdStateFlow = MoviesIdStateFlow.getCurrentIdMovies()

    private val _getMovieDetails = MutableLiveData<Resource<MovieDetails>>()
    val movieDetails: LiveData<Resource<MovieDetails>> = _getMovieDetails
    fun fetchMovieDetails() {
        viewModelScope.launch {
            _getMovieDetails.value = Resource.Loading()
            try {
                moviesIdStateFlow.collectLatest { moviesId ->
                    getDetailsMovieUseCase.invoke(moviesId).collect {
                        _getMovieDetails.value = it
                    }
                }
            } catch (e: Exception) {
                _getMovieDetails.value = Resource.Error("Failed to fetch now playing movie list")
            }
        }
    }

    private val _getCreditsCastMovieMovies = MutableLiveData<Resource<List<CastMovies>>>()
    val getCreditsCastMovieMovies: LiveData<Resource<List<CastMovies>>> = _getCreditsCastMovieMovies
    fun fetchCreditsActorMovies() {
        viewModelScope.launch {
            _getCreditsCastMovieMovies.value = Resource.Loading()
            try {
                moviesIdStateFlow.collectLatest { moviesId ->
                    getCreditsActorMovieUseCase.invoke(moviesId).collect {
                        _getCreditsCastMovieMovies.value = it
                    }
                }
            } catch (e: Exception) {
                _getCreditsCastMovieMovies.value = Resource.Error("Failed to fetch credits actor list")
            }
        }
    }

    private val _getRecommendationsMovies = MutableLiveData<Resource<List<Movies>>>()
    val getRecommendationsMovies: LiveData<Resource<List<Movies>>> get() = _getRecommendationsMovies
    fun fetchRecommendationsMovies() {
        viewModelScope.launch {
            _getRecommendationsMovies.value = Resource.Loading()
            try {
                moviesIdStateFlow.collectLatest { moviesId ->
                    recommendationsMovies_useCase.invoke(moviesId).collect {
                        _getRecommendationsMovies.value = it
                    }
                }
            } catch (e: java.lang.Exception) {
                _getRecommendationsMovies.value = Resource.Error("Failed to fetch now playing movie list")
            }
        }
    }

    private val _getMoviesByName = MutableLiveData<Resource<List<Movies>>>()
    val getMoviesByName: LiveData<Resource<List<Movies>>> get() = _getMoviesByName
    fun searchMoviesByName(moviesName: String) {
        viewModelScope.launch {
            _getMoviesByName.value = Resource.Loading()
            try {
                searchMovies_useCase.invoke(moviesName).collect {
                    _getMoviesByName.value = it
                }
            }  catch (e: Exception) {
                _getMoviesByName.value = Resource.Error("Failed to fetch now playing movie list")
            }
        }
    }

}