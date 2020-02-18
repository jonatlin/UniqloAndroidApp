package com.uniqlo.uniqloandroidapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.model.Item


class CartItemAdapter : ListAdapter<Item, CartItemAdapter.ItemViewHolder>(ItemDiffCallback) {

    override fun onBindViewHolder(holder: CartItemAdapter.ItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemAdapter.ItemViewHolder {

        val itemView = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.item_cart, parent, false)
        return CartItemAdapter.ItemViewHolder(itemView)

    }

    class ItemViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: Item) {

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