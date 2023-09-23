package com.raflisalam.moviesapp.core.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.moviesapp.core.R
import com.raflisalam.moviesapp.core.common.utils.OnItemDataClickListener
import com.raflisalam.moviesapp.core.common.utils.TimeUtils
import com.raflisalam.moviesapp.core.databinding.ItemSearchMovieResultBinding
import com.raflisalam.moviesapp.core.domain.model.Movies

class SearchMoviesAdapter(
    private val onItemDataClickListener: OnItemDataClickListener
): RecyclerView.Adapter<SearchMoviesAdapter.ViewHolder>() {

    private var listMovies = ArrayList<Movies>()

    fun setData(newListData: List<Movies>?) {
        if (newListData == null) return
        listMovies.clear()
        listMovies.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemSearchMovieResultBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movies) {
            binding.apply {
                val posterUrl = "${com.raflisalam.moviesapp.core.common.constant.Constants.path_image_base_url}${item.poster}"
                if (item.poster.isEmpty()) {
                    val defaultDrawableResId = R.drawable.default_poster
                    imagePoster.setImageResource(defaultDrawableResId)
                } else {
                    Glide.with(itemView.context)
                        .load(posterUrl)
                        .apply(RequestOptions())
                        .into(imagePoster)
                }
                titleMovies.text = item.title
                ratingMovies.text = item.rating.let { com.raflisalam.moviesapp.core.common.helper.Convert.roundDouble(it).toString() }
                if (item.genreId.isEmpty()) {
                    chipGenre.visibility = View.GONE
                } else {
                    chipGenre.text = com.raflisalam.moviesapp.core.common.helper.Convert.toGenres(item.genreId[0])
                }
                releaseDate.text = item.release_date.let { TimeUtils.formatDate(it) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchMovieResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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