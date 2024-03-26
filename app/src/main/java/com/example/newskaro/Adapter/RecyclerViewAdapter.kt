package com.example.newskaro.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newskaro.Data.Article
import com.example.newskaro.R
import kotlin.math.sign

class RecyclerViewAdapter(private val list : ArrayList<Article>) : RecyclerView.Adapter<Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.items_layout ,parent ,false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val current = list[position]
        if(current.title != "[Removed]" && current.content != "[Removed]"){
            holder.title.text = current.title
            holder.content.text = current.content

            Glide.with(holder.imageView.context)
                .load(current.urlToImage)
                .dontAnimate()
                .into(holder.imageView)
        }

    }
    fun updateData(newData: ArrayList<Article>){
        list.clear()
        list.addAll(newData)
        notifyDataSetChanged()

    }
}

class Holder(itemsView : View) :  RecyclerView.ViewHolder(itemsView){
    val imageView : ImageView = itemsView.findViewById(R.id.imURLIMAGE)
    val title : TextView = itemsView.findViewById(R.id.tvTile)
    val content : TextView = itemsView.findViewById(R.id.tvContent)
}
