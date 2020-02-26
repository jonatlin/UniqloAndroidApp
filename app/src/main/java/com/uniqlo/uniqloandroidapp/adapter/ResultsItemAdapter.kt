package com.uniqlo.uniqloandroidapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.model.Item
import com.uniqlo.uniqloandroidapp.ui.results.ResultsFragmentDirections
import kotlinx.android.synthetic.main.results_item.view.*
import timber.log.Timber

/**
 * Ad.kt adapter for DiscoverFragment.
 */
class ResultsItemAdapter : ListAdapter<Item, ResultsItemAdapter.ItemViewHolder>(ItemDiffCallback) {

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.bind(item)

        /* val results_item = getItem(position)

        holder.bind(results_item)*/


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val itemView = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.results_item, parent, false)
        return ItemViewHolder(itemView)

    }


    class ItemViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        /* init {


         }
 */
        /*binding.root.favorite_icon.setOnClickListener
        {
            val favorite = binding.results_item?.favorite ?: false
            binding.results_item?.favorite = !favorite
            Timber.d("favorite is %b", !favorite)

            if (!favorite)
                binding.root.favorite_icon.setImageResource(R.drawable.ic_icn_favorite_large_selected)
            else
                binding.root.favorite_icon.setImageResource(R.drawable.ic_icn_favorite_large_deselected)

        }*/


        fun bind(item: Item) {

            // show image
            Glide.with(itemView.context)
                .load(item.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.color.colorPrimaryLight) // doesn't work with resizing?
                .into(itemView.item_image)

            itemView.item_name.setText(item.name)
            itemView.item_price.setText(String.format("$%.2f", item.originalPrice))

            // change favorite icon when pressed
            itemView.favorite_icon.setOnClickListener {

                item.favorite = !item.favorite

                if (item.favorite)
                    itemView.favorite_icon.setImageResource(R.drawable.ic_icn_favorite_large_selected)
                else
                    itemView.favorite_icon.setImageResource(R.drawable.ic_icn_favorite_large_deselected)
                Timber.d("favorite set to %b", item.favorite)
            }

            itemView.card_view.setOnClickListener  {
                navigateToItemDetails(item, it)

            }

        }

        // opens a new page to view selected results_item
        private fun navigateToItemDetails(
            item: Item,
            view: View
        ) {
            val direction =
                ResultsFragmentDirections.actionFragmentResultsDestToFragmentItemDetailsDest(
                    item.itemId)

            // motion settings for navigation
            val extras =  FragmentNavigatorExtras(view.item_image to view.item_image.transitionName)


            Timber.d("navigate to results_item details with id: %s", item.itemId)
            view.findNavController().navigate(direction, extras)

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