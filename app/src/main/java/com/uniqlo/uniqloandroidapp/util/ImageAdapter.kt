package com.uniqlo.uniqloandroidapp.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uniqlo.uniqloandroidapp.model.Ad
import com.uniqlo.uniqloandroidapp.adapter.AdAdapter
import com.uniqlo.uniqloandroidapp.adapter.ResultsItemAdapter
import com.uniqlo.uniqloandroidapp.model.Item

@BindingAdapter("app:listItems")
@Suppress("UNCHECKED_CAST")
fun setItems(listView: RecyclerView, items: List<*>) {
//    find better way
    if(listView.adapter is AdAdapter)
        (listView.adapter as AdAdapter).submitList(items as List<Ad>)
    else if(listView.adapter is ResultsItemAdapter)
        (listView.adapter as ResultsItemAdapter).submitList(items as List<Item>)
}

@BindingAdapter("imageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context)
            .load(imageUrl)
            .override(view.width) // temp fix, doesn't work for portrait pictures
            .transition(DrawableTransitionOptions.withCrossFade())
//            .placeholder(R.color.colorPrimaryLight) // doesn't work with resizing?
//            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }
}