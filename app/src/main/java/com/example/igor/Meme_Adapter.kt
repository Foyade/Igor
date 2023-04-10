package com.example.igor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MemeAdapter(private val memeList: List<String>) : RecyclerView.Adapter<MemeAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memeitem, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return memeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(memeList[position])
            .centerCrop()
            .into(holder.memeImage)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val memeImage: ImageView = view.findViewById(R.id.image)
    }
}