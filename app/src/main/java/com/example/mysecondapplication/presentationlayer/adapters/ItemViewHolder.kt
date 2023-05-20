package com.example.mysecondapplication.presentationlayer.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.Toast
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

    lateinit var context: Context
    var position: Int? = null

    private val imageLoader by lazy { Glide.with(image) }

    private var loaded: Boolean = true

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
                    loaded = false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    loaded = true
                    return false
                }
            })
            .error(R.drawable.image_load_failed)
            .into(image)
        image.adjustViewBounds = true
    }

    fun setReloadClickListenerToImage(item: Item) {
        image.setOnClickListener {
            if (loaded) {
                Toast.makeText(context, position!!.toString(), Toast.LENGTH_SHORT).show()
            } else {
                bind(item)
            }
        }
    }
}