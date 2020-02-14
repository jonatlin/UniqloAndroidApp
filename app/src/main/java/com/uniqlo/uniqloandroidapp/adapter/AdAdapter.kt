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
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.ui.discover.DiscoverFragmentDirections
import com.uniqlo.uniqloandroidapp.ui.discover.DiscoverViewModel
import kotlinx.android.synthetic.main.ad.view.*
import kotlinx.android.synthetic.main.item.view.*
import timber.log.Timber

/**
 * Ad.kt adapter for DiscoverFragment.
 */
class AdAdapter : ListAdapter<Ad, AdAdapter.AdViewHolder>(
    AdDiffCallback()
) {

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        val item = getItem(position)

        // Image title. Does it look better?
        /*val textColor: String = "#" + item.textColor

        val shortDescription: TextView = holder.itemView.findViewById<TextView>(R.id.picture_text)
        shortDescription.setTextColor(Color.parseColor(textColor))*/

        /* if(item.showText==1)
             shortDescription.visibility = View.VISIBLE
         else
             shortDescription.visibility = View.INVISIBLE*/

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {

        val itemView = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.ad, parent, false)
        return AdViewHolder(itemView)

        /*val itemView = LayoutInflater.from(
            parent.context).inflate(R.layout.ad, parent, false)
        return ViewHolder(itemView)*/
    }

    class AdDiffCallback : DiffUtil.ItemCallback<Ad>() {
        override fun areItemsTheSame(oldItem: Ad, newItem: Ad): Boolean {
            return oldItem.adId == newItem.adId
//            return true
        }

        override fun areContentsTheSame(oldItem: Ad, newItem: Ad): Boolean {
            return oldItem == newItem
        }

    }

    class AdViewHolder constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(ad: Ad) {
            itemView.root_view.setOnClickListener  {
                navigateToAdItems(ad, it)

            }

            // load picture
            Glide.with(itemView.context)
                .load(ad.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.color.colorPrimaryLight) // doesn't work with resizing?
                .into(itemView.ad_image)

            itemView.ad_name.text = ad.name
            itemView.ad_description.text=ad.description

        }

        // go to results page when ad selected
        private fun navigateToAdItems(
            ad: Ad,
            view: View
        ) {
            val direction =
                DiscoverFragmentDirections.actionFragmentDiscoverDestToFragmentResultsDest(
                    ad.adId
                )

            Timber.d("navigate to add items: ${ad.adId}")
            view.findNavController().navigate(direction)

        }

    }



}