package com.inxparticle.dfakestore.adapter

import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.inxparticle.dfakestore.R
import com.inxparticle.dfakestore.api.model.category.CategoryResponse
import com.inxparticle.dfakestore.listener.HomeRecyclerviewListener
import com.squareup.picasso.Picasso

class CategoryAdapter(val list: CategoryResponse,val listener:HomeRecyclerviewListener) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.category_image_view)
        val textView: TextView = itemView.findViewById(R.id.category_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_layout, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = list[position]
        holder.textView.text = model

        val url = "https://cdn.pixabay.com/photo/2023/12/04/16/15/ai-generated-8429782_1280.jpg"

        Picasso.get()
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageView);

        holder.itemView.setOnClickListener{
            listener.itemClick(position)
        }
    }
}