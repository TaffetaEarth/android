package com.example.mysecondapplication.presentationlayer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.presentationlayer.adapters.ItemDifferCallback
import com.example.mysecondapplication.R
import com.example.mysecondapplication.objects.Item

class ItemAdapter : ListAdapter<Item, ItemViewHolder>(ItemDifferCallback()) {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.context = context
        holder.position = position
        holder.setReloadClickListenerToImage(item)
    }
}