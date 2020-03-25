package com.vaishnavi.telstratest.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.vaishnavi.telstratest.R
import com.vaishnavi.telstratest.databinding.MainListItemBinding
import com.vaishnavi.telstratest.model.Facts

class MainAdapter(val context: Context, private val list: List<Facts>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    class MainViewHolder(listBinding: MainListItemBinding) :
        RecyclerView.ViewHolder(listBinding.root) {
        var listItemBinding: MainListItemBinding = listBinding //set up view holder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        //set up binding
        val listBinding: MainListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.main_list_item, parent, false
        )
        return MainViewHolder(listBinding)
    }

    override fun getItemCount(): Int {
        //return the size of the list
        return list.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) =//bind view to data
        if (list[position].title != null) {
            holder.listItemBinding.facts = Facts(
                list[position].title,
                list[position].description,
                list[position].imageHref
            )
        } else {
            holder.listItemBinding.title.visibility = View.GONE
            holder.listItemBinding.desc.visibility = View.GONE
            holder.listItemBinding.image.visibility = View.GONE
        }
}
