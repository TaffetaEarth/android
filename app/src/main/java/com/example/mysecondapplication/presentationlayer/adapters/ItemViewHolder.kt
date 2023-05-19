package com.example.mysecondapplication.presentationlayer.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.mysecondapplication.R
import com.example.mysecondapplication.objects.Item


class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.image)

    private val imageLoader by lazy { Glide.with(image) }

    fun bind(item: Item) {
        imageLoader
            .load(item.imageValue())
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.loader_balls)
            .transition(DrawableTransitionOptions.withCrossFade())
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    image.isEnabled = true
                    return false
                }


                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    image.isEnabled = false
                    return false
                }
            })
            .error(R.drawable.image_load_failed)
            .into(image)
        image.adjustViewBounds = true
    }

    fun setOnClickListenerToImage(item: Item) {
        image.setOnClickListener {
            bind(item)
        }
    }
}