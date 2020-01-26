package com.uniqlo.uniqloandroidapp.util

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.Target
import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.adapter.AdAdapter
import com.uniqlo.uniqloandroidapp.adapter.ItemAdapter
import com.uniqlo.uniqloandroidapp.data.Item

@BindingAdapter("app:listItems")
@Suppress("UNCHECKED_CAST")
fun setItems(listView: RecyclerView, items: List<*>) {
//    find better way
    if(listView.adapter is AdAdapter)
        (listView.adapter as AdAdapter).submitList(items as List<Ad>)
    else if(listView.adapter is ItemAdapter)
        (listView.adapter as ItemAdapter).submitList(items as List<Item>)


}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .override(view.width) // temp fix, doesn't work for portrait pictures
            .transition(DrawableTransitionOptions.withCrossFade())
//            .placeholder(R.drawable.placeholder) // doesn't work with resizing?
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }
}