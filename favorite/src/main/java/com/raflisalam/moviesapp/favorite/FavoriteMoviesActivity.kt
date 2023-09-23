package com.raflisalam.moviesapp.favorite

import android.content.Intent
import android.graphics.Canvas
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raflisalam.moviesapp.R
import com.raflisalam.moviesapp.core.common.utils.MoviesIdStateFlow
import com.raflisalam.moviesapp.core.common.utils.OnItemDataClickListener
import com.raflisalam.moviesapp.core.di.FavoriteModuleDependencies
import com.raflisalam.moviesapp.core.presentation.ui.FavoriteMoviesAdapter
import com.raflisalam.moviesapp.detail.DetailMoviesActivity
import com.raflisalam.moviesapp.favorite.databinding.ActivityFavoriteMoviesBinding
import com.raflisalam.moviesapp.favorite.di.DaggerFavoriteComponent
import com.raflisalam.moviesapp.favorite.viewmodel.FavoriteViewModel
import com.raflisalam.moviesapp.favorite.viewmodel.ViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteMoviesActivity : AppCompatActivity(), OnItemDataClickListener {

    private lateinit var binding: ActivityFavoriteMoviesBinding
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: FavoriteViewModel by viewModels { factory }
    private lateinit var adapter: FavoriteMoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = FavoriteMoviesAdapter(this)

        initFavoriteMovies()
        swipeToDelete()

        binding.toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun swipeToDelete() {
        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val movie = adapter.currentList[position]
                viewModel.deleteFavoriteMovieById(movie.id)
            }

            override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                val swipeDecorator = RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(ContextCompat.getColor(this@FavoriteMoviesActivity, R.color.color_background_swipe))
                    .addActionIcon(R.drawable.ic_delete)
                    .setIconHorizontalMargin(resources.getDimensionPixelSize(R.dimen.icon_horizontal_margin))
                    .create()
                swipeDecorator.decorate()
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    private fun initFavoriteMovies() {
        lifecycleScope.launch {
            viewModel.getFavoriteMovies.collect { data ->
                adapter.submitList(data)
                initRecyclerView()
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = this@FavoriteMoviesActivity.adapter
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