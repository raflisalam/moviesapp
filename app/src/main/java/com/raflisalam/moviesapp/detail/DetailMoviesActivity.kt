package com.raflisalam.moviesapp.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.chip.Chip
import com.raflisalam.moviesapp.core.R
import com.raflisalam.moviesapp.core.common.constant.Constants
import com.raflisalam.moviesapp.core.common.helper.Convert
import com.raflisalam.moviesapp.core.common.utils.TimeUtils
import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.FavoriteMovies
import com.raflisalam.moviesapp.core.domain.model.MovieDetails
import com.raflisalam.moviesapp.core.presentation.ui.MoviesCastAdapter
import com.raflisalam.moviesapp.core.presentation.ui.MoviesRecommendationsAdapter
import com.raflisalam.moviesapp.core.presentation.viewmodel.FavoriteMoviesViewModel
import com.raflisalam.moviesapp.core.presentation.viewmodel.MoviesViewModel
import com.raflisalam.moviesapp.databinding.ActivityDetailMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailMoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMoviesBinding
    private val viewModel: FavoriteMoviesViewModel by viewModels()
    private val detailViewModel: MoviesViewModel by viewModels()

    private lateinit var actorAdapter: MoviesCastAdapter
    private lateinit var recommendationsAdapter: MoviesRecommendationsAdapter

    private var favoriteState: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFetchMoviesDetails()
        setupButton()
    }

    private fun initFetchMoviesDetails() {
        detailViewModel.fetchMovieDetails()
        detailViewModel.movieDetails.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    val movies = it.data
                    updateUI(movies)
                    binding.apply {
                        loading.visibility = View.GONE
                        textHeadSynopsis.visibility = View.VISIBLE
                        btnBack.visibility = View.VISIBLE
                        iconRating.visibility = View.VISIBLE
                        iconTime.visibility = View.VISIBLE
                        rvActor.visibility = View.VISIBLE
                        textHeadActors.visibility = View.VISIBLE
                        recyclerView2.visibility = View.VISIBLE
                        layoutAboutFilm.visibility = View.VISIBLE
                        btnWatchlist.visibility = View.VISIBLE
                    }
                }
                is Resource.Error -> {
                    binding.loading.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setupButton() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun updateUI(data: MovieDetails?) {
        if (data != null) {
            binding.apply {
                Glide.with(this@DetailMoviesActivity)
                    .load("${Constants.path_image_base_url}${data.backdrop_poster}")
                    .apply(RequestOptions())
                    .into(imagePoster)
                titleMovies.text = data.title
                ratingMovies.text = "${Convert.roundDouble(data.rating)} |"
                ratingVotes.text = data.rating_vote.toString()
                timeMovies.text = TimeUtils.formatRuntimeToHoursMinutes(data.runtime)
                overviewMovies.text = data.synopsis
                originalTitle.text = data.original_title
                releaseDate.text = TimeUtils.formatDate(data.release_date)
                production.text = data.productionCountry[0].name
                tagline.text = data.tagline
                data.genres.forEach {
                    binding.genre.text = it.name
                    chipGenreMovies.addView(createChipGenre(it.name))
                }
                fetchActorMovies()
                fetchRecommendationsMovies()
                checkMoviesIsFavorite(data)
                favoriteMovies(data)
            }
        }

    }

    private fun checkMoviesIsFavorite(data: MovieDetails) {
        lifecycleScope.launch {
            val isFavorite = viewModel.getFavoriteMovies.firstOrNull()?.any { it.id == data.moviesId} == true
            if (isFavorite) {
                favoriteState = true
                binding.btnWatchlist.isChecked = true
            } else {
                favoriteState = false
                binding.btnWatchlist.isChecked = false
            }
        }
    }

    private fun favoriteMovies(data: MovieDetails) {
        binding.btnWatchlist.setOnClickListener {
            val movies = FavoriteMovies(
                id = data.moviesId,
                title = data.title,
                image_poster = data.image_poster,
                description = data.synopsis,
                rating = data.rating,
                release_date = data.release_date
            )
            viewModel.toggleFavoriteMovies(movies)
        }
    }

    private fun createChipGenre(genre: String): Chip {
        val chip  = layoutInflater.inflate(R.layout.item_chip_genre, binding.chipGenreMovies, false) as Chip
        chip.text = genre
        return chip
    }

    private fun fetchRecommendationsMovies() {
        detailViewModel.fetchRecommendationsMovies()
        detailViewModel.getRecommendationsMovies.observe(this) {
            when (it) {
                is Resource.Success -> {
                    val data = it.data
                    if (data != null) {
                        binding.textHeadRecommendations.visibility = View.VISIBLE
                        recommendationsAdapter = MoviesRecommendationsAdapter(data)
                        initRecyclerViewRecommendations()
                    } else {
                        binding.textHeadRecommendations.visibility = View.INVISIBLE
                    }
                }
                else -> {}
            }
        }
    }

    private fun initRecyclerViewRecommendations() {
        binding.apply {
            recyclerView2.layoutManager = LinearLayoutManager(this@DetailMoviesActivity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView2.adapter = recommendationsAdapter
        }
    }

    private fun fetchActorMovies() {
        detailViewModel.fetchCreditsActorMovies()
        detailViewModel.getCreditsCastMovieMovies.observe(this) {
            when (it) {
                is Resource.Success -> {
                    val data = it.data
                    if (data != null) {
                        actorAdapter = MoviesCastAdapter(data)
                        initRecyclerViewActor()
                    }
                }

                else -> {}
            }
        }
    }

    private fun initRecyclerViewActor() {
        binding.apply {
            rvActor.layoutManager = LinearLayoutManager(this@DetailMoviesActivity, LinearLayoutManager.HORIZONTAL, false)
            rvActor.adapter = actorAdapter
        }
    }


}