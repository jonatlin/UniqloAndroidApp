package com.uniqlo.uniqloandroidapp.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.uniqlo.uniqloandroidapp.data.TopToolbarItem

class TopToolbarAdapter(

) : ListAdapter<TopToolbarItem, TopToolbarAdapter.ViewHolder>(ProfileDiffCallback()) {




    class ViewHolder private constructor(val binding: ProfileCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: HomeViewModel, item: TopToolbarItem) {

            binding.viewmodel = viewModel
            binding.profile = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ProfileCardBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }

    }


}

class ProfileDiffCallback : DiffUtil.ItemCallback<Profile>() {
    override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
        return oldItem == newItem
    }

}