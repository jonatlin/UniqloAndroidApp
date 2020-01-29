package com.uniqlo.uniqloandroidapp.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.data.Item
import kotlinx.android.synthetic.main.item_preview.view.*


class ItemPreviewAdapter :
    ListAdapter<Item, ItemPreviewAdapter.ItemPreviewViewHolder>(ItemDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemPreviewViewHolder {
        val itemView = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.item_preview, parent, false)
        return ItemPreviewViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemPreviewViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    class ItemPreviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item) {
            val url = item.imageUrl
            showImage(url)
        }

        private fun showImage(url: String) {

            if (!url.isNullOrEmpty()) {
                Glide.with(itemView.context)
                    .load(url)
                    .transition(DrawableTransitionOptions.withCrossFade())
//            .placeholder(R.color.colorPrimaryLight) // doesn't work with resizing?
                    .into(itemView.image_preview)
            }

        }

    }

    private object ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.itemId == newItem.itemId
//            return true
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

}