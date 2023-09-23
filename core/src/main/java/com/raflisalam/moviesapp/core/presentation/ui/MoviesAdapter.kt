package com.raflisalam.moviesapp.core.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.moviesapp.core.common.constant.Constants
import com.raflisalam.moviesapp.core.common.utils.OnItemDataClickListener
import com.raflisalam.moviesapp.core.databinding.ItemMoviesPopularBinding
import com.raflisalam.moviesapp.core.domain.model.Movies

class MoviesAdapter(
    private val onItemDataClickListener: OnItemDataClickListener
): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private var listMovies = ArrayList<Movies>()

    fun setData(newListData: List<Movies>?) {
        if (newListData == null) return
        listMovies.clear()
        listMovies.addAll(newListData)
    }

    inner class ViewHolder(private val binding: ItemMoviesPopularBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            binding.apply {
                val posterUrl = "${Constants.path_image_base_url}${item.poster}"
                Glide.with(itemView.context)
                    .load(posterUrl)
                    .apply(RequestOptions())
                    .into(imagePoster)
                title.text = item.title
                if (item.genreId.isNotEmpty()) {
                    genre.text = com.raflisalam.moviesapp.core.common.helper.Convert.toGenres(item.genreId[0])
                } else {
                    genre.text = ""
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMoviesPopularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listMovies[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onItemDataClickListener.onItemMoviesClick(item.id)
        }
    }

    override fun getItemCount(): Int = listMovies.size

}