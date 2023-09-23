package com.raflisalam.moviesapp.core.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.moviesapp.core.common.constant.Constants
import com.raflisalam.moviesapp.core.common.utils.OnItemDataClickListener
import com.raflisalam.moviesapp.core.databinding.ItemMoviesTopRatedBinding
import com.raflisalam.moviesapp.core.domain.model.Movies

class MoviesTopRatedAdapter(
    private val onItemDataClickListener: OnItemDataClickListener
): RecyclerView.Adapter<MoviesTopRatedAdapter.ViewHolder>() {

    private var listMovies = ArrayList<Movies>()

    fun setData(newListData: List<Movies>?) {
        if (newListData == null) return
        listMovies.clear()
        listMovies.addAll(newListData)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMoviesTopRatedBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            binding.apply {
                val posterUrl = "${Constants.path_image_base_url}${item.poster}"
                Glide.with(itemView.context)
                    .load(posterUrl)
                    .apply(RequestOptions())
                    .into(imagePoster)
                title.text = item.title
                val ratingPercentage = (item.rating * 10).toInt()
                ratingBar.progress = ratingPercentage
                ratingValues.text = "$ratingPercentage%"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviesTopRatedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            onItemDataClickListener.onItemMoviesClick(movie.id)
        }
    }

}