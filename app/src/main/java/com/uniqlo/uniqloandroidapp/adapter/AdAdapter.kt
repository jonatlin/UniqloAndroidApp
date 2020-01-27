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
import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.databinding.AdBinding
import com.uniqlo.uniqloandroidapp.ui.discover.DiscoverFragmentDirections
import com.uniqlo.uniqloandroidapp.ui.discover.DiscoverViewModel
import timber.log.Timber

/**
 * Ad.kt adapter for DiscoverFragment.
 */
class AdAdapter(private val viewModel: DiscoverViewModel) : ListAdapter<Ad, AdAdapter.ViewHolder>(
    AdDiffCallback()
) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        // works but images resizing causes scroll bar to jump. Also ImageView must be parent.
       /* var image: ImageView = holder.itemView.findViewById<ImageView>(R.id.image)
        image.layout(0,0,0,0)*/

        // Image title. Does it look better?
        val textColor: String = "#" + item.textColor

        val shortDescription: TextView =  holder.itemView.findViewById<TextView>(R.id.picture_text)
        shortDescription.setTextColor(Color.parseColor(textColor))

       /* if(item.showText==1)
            shortDescription.visibility = View.VISIBLE
        else
            shortDescription.visibility = View.INVISIBLE*/

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(
            parent
        )
    }

    class ViewHolder private constructor(val binding: AdBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.ad?.let { ad ->
                    navigateToAdItems(ad, it)
                }
            }
        }

        fun bind(item: Ad) {
            binding.ad = item
            binding.executePendingBindings()
        }

        private fun navigateToAdItems(
            ad: Ad,
            view: View
        ) {
            val direction =
                DiscoverFragmentDirections.actionFragmentDiscoverDestToFragmentResultsDest(
                    ad.adId, null)

            Timber.d("navigate to search results of Ad: ${ad.adId}")
            view.findNavController().navigate(direction)


        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AdBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(
                    binding
                )
            }
        }

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

}