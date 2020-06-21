package com.android.androidexercise.adapter;


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.android.androidexercise.R
import com.android.androidexercise.model.Item
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item.view.*


class ListViewAdapter(private val context: Context) :
        RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {


    private var list: List<Item> = ArrayList()

    //setting data in list and notify for changes
    fun setDataList(list: List<Item>) {
        this.list = list
        notifyDataSetChanged()
    }

    //listview item display here
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(context).inflate(
                        R.layout.list_item,
                        parent,
                        false
                )
        )
    }

    //item count
    override fun getItemCount(): Int {
        return list.size
    }


    //loading the image using glide library
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.description.text = list[position].description
        Glide.with(context).asDrawable().load(list[position].imageHref).into(holder.image)

    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title = v.title!!
        val description = v.description!!
        val image = v.image!!
    }


}


