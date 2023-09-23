package com.raflisalam.moviesapp.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.raflisalam.moviesapp.R
import com.raflisalam.moviesapp.core.common.utils.MoviesIdStateFlow
import com.raflisalam.moviesapp.core.common.utils.OnItemDataClickListener
import com.raflisalam.moviesapp.core.data.remote.Resource
import com.raflisalam.moviesapp.core.domain.model.Movies
import com.raflisalam.moviesapp.core.presentation.ui.MoviesAdapter
import com.raflisalam.moviesapp.core.presentation.ui.MoviesTopRatedAdapter
import com.raflisalam.moviesapp.core.presentation.ui.SearchMoviesAdapter
import com.raflisalam.moviesapp.core.presentation.viewmodel.MoviesViewModel
import com.raflisalam.moviesapp.databinding.ActivityHomeBinding
import com.raflisalam.moviesapp.detail.DetailMoviesActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), OnItemDataClickListener {

    private lateinit var binding: ActivityHomeBinding

    private val viewModel: MoviesViewModel by viewModels()
    private lateinit var adapter: MoviesAdapter
    private lateinit var topRatedAdapter: MoviesTopRatedAdapter
    private lateinit var searchAdapter: SearchMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = MoviesAdapter(this)
        topRatedAdapter = MoviesTopRatedAdapter(this)
        searchMovies()
        initFetchMovies()

        setupMenu()
    }

    private fun setupMenu() {
        binding.apply {
            val triggerPopup = iconMenu
            val popupMenu = PopupMenu(this@HomeActivity, triggerPopup)

            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menu ->
                when (menu.itemId) {
                    R.id.favorite -> {
                        val uri = Uri.parse("moviesapp://favorite")
                        startActivity(Intent(Intent.ACTION_VIEW, uri))
                        true
                    }
                    else -> false
                }
            }
            triggerPopup.setOnClickListener {
                popupMenu.show()
            }
        }
    }

    private fun searchMovies() {
        binding.apply {
            searchView.setOnClickListener { searchView.isIconified = false }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchView.setQuery("", false)
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText?.isEmpty() == true) {
                        binding.textHeadPopular.visibility = View.VISIBLE
                        binding.textContentPopular.visibility = View.VISIBLE
                        initFetchMovies()
                    } else {
                        binding.textHeadPopular.visibility = View.GONE
                        binding.textContentPopular.visibility = View.GONE
                        if (newText != null) {
                            initFetchSearchResult(newText)
                        }
                    }
                    return true
                }
            })
        }
    }

    private fun initFetchSearchResult(query: String) {
        viewModel.searchMoviesByName(query)
        viewModel.getMoviesByName.observe(this) {
            when (it) {
                is Resource.Success -> {
                    val data = it.data
                    if (data != null) {
                        showSearchResult(data)
                    }
                }
                else -> {}
            }
        }
    }


    private fun showSearchResult(data: List<Movies>) {
        searchAdapter = SearchMoviesAdapter(this)
        searchAdapter.setData(data)
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.VERTICAL, false)
            recyclerView.adapter = searchAdapter
        }
    }


    private fun initFetchMovies() {
        viewModel.fetchPopularMovies()
        viewModel.getPopularMovies.observe(this) { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> {
                        binding.loading.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.loading.visibility = View.GONE
                        adapter.setData(movies.data)
                        initRecyclerView()
                    }
                    is Resource.Error -> {
                        binding.loading.visibility = View.GONE
                        Toast.makeText(this, "Oops.. something went wrong", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        viewModel.fetchTopRatedMovies()
        viewModel.getTopRatedMovies.observe(this) { movies ->
            when (movies) {
                is Resource.Success -> {
                    binding.loading.visibility = View.GONE
                    topRatedAdapter.setData(movies.data)
                    initRecyclerTopRated()
                }

                is Resource.Error -> {}
                is Resource.Loading -> {}
            }
        }
    }

    private fun initRecyclerTopRated() {
        binding.apply {
            recyclerView2.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView2.adapter = topRatedAdapter
        }
    }

    private fun initRecyclerView() {
        binding.apply {
            recyclerView.layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = adapter
        }
    }

    override fun onItemMoviesClick(id: Int) {
        MoviesIdStateFlow.onMoviesSelected(id)
        showMovieDetails()
    }

    private fun showMovieDetails() {
        startActivity(Intent(this, DetailMoviesActivity::class.java))
    }
}