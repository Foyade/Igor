package com.example.igor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterMeme(private val memeList: MutableList<memeItem>) : RecyclerView.Adapter<AdapterMeme.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val memeImage: ImageView
        val memeId: TextView
        val memeName: TextView

        init {
            memeImage = view.findViewById(R.id.image)
            memeId = view.findViewById(R.id.id)
            memeName = view.findViewById(R.id.name)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memeitem, parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return memeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = memeList.get(position)
        holder.memeName.text = item.name
        holder.memeId.text = item.id
        Glide.with(holder.itemView)
            .load(memeList[position].image)
            .centerCrop()
            .into(holder.memeImage)
    }


}