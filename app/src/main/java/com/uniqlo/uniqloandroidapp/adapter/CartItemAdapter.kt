package com.uniqlo.uniqloandroidapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.database.CartItemEntity
import com.uniqlo.uniqloandroidapp.ui.cart.CartViewModel
import kotlinx.android.synthetic.main.cart_item.view.*
import timber.log.Timber

class CartItemAdapter(val cartViewModel: CartViewModel) : ListAdapter<CartItemEntity, CartItemAdapter.ItemViewHolder>(ItemDiffCallback) {

    override fun onBindViewHolder(holder: CartItemAdapter.ItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item, cartViewModel)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartItemAdapter.ItemViewHolder {

        val itemView = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.cart_item, parent, false)
        return CartItemAdapter.ItemViewHolder(itemView)

    }

    class ItemViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(item: CartItemEntity, cartViewModel: CartViewModel) {
            Glide.with(itemView.context)
                .load(item.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.color.colorPrimaryLight) // doesn't work with resizing?
                .into(itemView.item_image)

            itemView.item_name.text = item.name
            itemView.item_price.text = String.format("$%.2f",item.price)
            itemView.item_quantity.text = String.format("x%d",item.quantity)

            itemView.delete_button.setOnClickListener {
                removeItemFromCart(item, cartViewModel)
            }
        }

        private fun removeItemFromCart(item: CartItemEntity, cartViewModel: CartViewModel) {
            cartViewModel.removeItemFromCart(item)
            Timber.d("item deleted")
        }


    }


    private object ItemDiffCallback : DiffUtil.ItemCallback<CartItemEntity>() {
        override fun areItemsTheSame(oldItem: CartItemEntity, newItem: CartItemEntity): Boolean {
            return oldItem.itemId == newItem.itemId
//            return true
        }

        override fun areContentsTheSame(oldItem: CartItemEntity, newItem: CartItemEntity): Boolean {
            return oldItem == newItem
        }

    }
}