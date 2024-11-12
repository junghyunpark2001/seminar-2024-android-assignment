package com.wafflestudio.waffleseminar2024.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.R

class favoriteRecyclerViewAdapter(
    private val movieList: List<Movie>,
) : RecyclerView.Adapter<favoriteRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.itemImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("items", movieList.size.toString())

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_result_recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = movieList[position]
        Log.d("bind","here")

        val imageUrl = "https://image.tmdb.org/t/p/original" + currentItem.poster_path
        holder.imageView.load(imageUrl)

    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}