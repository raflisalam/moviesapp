package com.raflisalam.moviesapp.core.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.moviesapp.core.common.utils.OnItemDataClickListener
import com.raflisalam.moviesapp.core.data.local.entity.MoviesEntity
import com.raflisalam.moviesapp.core.databinding.ItemFavoriteMoviesBinding
import com.raflisalam.moviesapp.core.domain.model.FavoriteMovies

class FavoriteMoviesAdapter(
    private val onItemDataClickListener: OnItemDataClickListener
): ListAdapter<FavoriteMovies, FavoriteMoviesAdapter.ViewHolder>(
    DIFF_CALLBACK
) {

    inner class ViewHolder(private val binding: ItemFavoriteMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteMovies) {
            binding.apply {
                val posterUrl = "${com.raflisalam.moviesapp.core.common.constant.Constants.path_image_base_url}${item.image_poster}"
                Glide.with(itemView.context)
                    .load(posterUrl)
                    .apply(RequestOptions())
                    .into(imagePoster)
                titleMovies.text = item.title
                ratingMovies.text = item.rating.toString()
                releaseDate.text = item.release_date
                synopsisMovies.text = item.description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemDataClickListener.onItemMoviesClick(item.id)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FavoriteMovies>() {
            override fun areItemsTheSame(
                oldItem: FavoriteMovies,
                newItem: FavoriteMovies
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: FavoriteMovies,
                newItem: FavoriteMovies
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

}