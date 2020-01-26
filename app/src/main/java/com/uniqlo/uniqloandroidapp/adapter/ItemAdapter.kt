package com.uniqlo.uniqloandroidapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.data.Item
import com.uniqlo.uniqloandroidapp.databinding.ItemBinding
import com.uniqlo.uniqloandroidapp.ui.discover.DiscoverFragmentDirections
import com.uniqlo.uniqloandroidapp.ui.discover.DiscoverViewModel
import kotlinx.android.synthetic.main.item.view.*
import timber.log.Timber

/**
 * Ad.kt adapter for DiscoverFragment.
 */
class ItemAdapter : ListAdapter<Item, ItemAdapter.ViewHolder>(
    ItemDiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(
            parent
        )
    }

    class ViewHolder private constructor(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.item?.let { item ->
                    navigateToItemDetails(item, it)
                }
            }

            binding.root.favorite_icon.setOnClickListener {
                val favorite = binding.item?.favorite ?: false

                binding.item?.favorite = !favorite
                if(favorite)
                    binding.root.favorite_icon.setImageResource(R.drawable.ic_icn_favorite_large_selected)
                else
                    binding.root.favorite_icon.setImageResource(R.drawable.ic_icn_favorite_large_deselected)

            }
        }

        fun bind(item: Item) {
            binding.item = item
            binding.executePendingBindings()
        }

        private fun navigateToItemDetails(
            item: Item,
            view: View
        ) {
            /*val direction =
                DiscoverFragmentDirections.actionFragmentDiscoverDestToFragmentResultsDest(
                    ad.adId, null)

            Timber.d("navigate to search results")
            view.findNavController().navigate(direction)*/

        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(
                    binding
                )
            }
        }

    }

    class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem.itemId == newItem.itemId
//            return true
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

    }

}