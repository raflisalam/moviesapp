package com.raflisalam.moviesapp.core.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.moviesapp.core.R
import com.raflisalam.moviesapp.core.common.constant.Constants
import com.raflisalam.moviesapp.core.databinding.ItemMoviesRecommendationsBinding
import com.raflisalam.moviesapp.core.domain.model.Movies

class MoviesRecommendationsAdapter(
    private var listMovies: List<Movies>
) : RecyclerView.Adapter<MoviesRecommendationsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemMoviesRecommendationsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            binding.apply {
                val posterUrl = "${Constants.path_image_base_url}${item.poster}"
                if (item.poster.isBlank()) {
                    val defaultDrawableResId = R.drawable.default_poster
                    imagePoster.setImageResource(defaultDrawableResId)
                } else {
                    Glide.with(itemView.context)
                        .load(posterUrl)
                        .apply(RequestOptions())
                        .into(imagePoster)
                }
                title.text = item.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviesRecommendationsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.bind(item)
    }

}