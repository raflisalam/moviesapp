package com.raflisalam.moviesapp.core.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raflisalam.moviesapp.core.R
import com.raflisalam.moviesapp.core.common.constant.Constants
import com.raflisalam.moviesapp.core.databinding.ItemCastMoviesBinding
import com.raflisalam.moviesapp.core.domain.model.CastMovies

class MoviesCastAdapter(
    private var listCastMovies: List<CastMovies>
): RecyclerView.Adapter<MoviesCastAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemCastMoviesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CastMovies) {
            binding.apply {
                val profileUrl = "${Constants.path_image_base_url}${item.profilePics}"
                if (item.profilePics.isBlank()) {
                    val defaultDrawableResId = R.drawable.default_profile
                    imageActor.setImageResource(defaultDrawableResId)
                } else {
                    Glide.with(itemView.context)
                        .load(profileUrl)
                        .apply(RequestOptions())
                        .into(imageActor)
                }
                nameActor.text = item.name
                nameCharacter.text = "as ${item.nameCharacter}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCastMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listCastMovies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listCastMovies[position]
        holder.bind(item)
    }
}