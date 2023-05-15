package com.example.mysecondapplication.presentationlayer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mysecondapplication.R
import com.example.mysecondapplication.objects.Item
import com.example.myapplication.presentationlayer.adapters.ItemDifferCallback

class ItemAdapter: ListAdapter<Item, ItemViewHolder>(ItemDifferCallback()) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
		return ItemViewHolder(view)
	}

	override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
		val item = getItem(position)
		holder.bind(item)
		holder.setOnClickListenerToImage(item)
	}
}