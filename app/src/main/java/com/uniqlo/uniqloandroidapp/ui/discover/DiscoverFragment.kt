package com.uniqlo.uniqloandroidapp.ui.discover


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.dropbox.android.external.store4.StoreResponse
import com.google.android.material.appbar.AppBarLayout
import com.uniqlo.uniqloandroidapp.MainActivity
import com.uniqlo.uniqloandroidapp.R

import com.uniqlo.uniqloandroidapp.adapter.AdAdapter
import com.uniqlo.uniqloandroidapp.adapter.ItemPreviewAdapter
import com.uniqlo.uniqloandroidapp.data.Ad
import com.uniqlo.uniqloandroidapp.data.Item
import com.uniqlo.uniqloandroidapp.databinding.FragmentDiscoverBinding
import kotlinx.android.synthetic.main.fragment_discover.*
import timber.log.Timber

/**
 * Shows ads to user
 */
class DiscoverFragment : Fragment() {

    private lateinit var dataBinding: FragmentDiscoverBinding
//    private lateinit var epoxyView: EpoxyRecyclerView
    private lateinit var viewModelFactory: DiscoverViewModelFactory
    private lateinit var viewModel: DiscoverViewModel

    private lateinit var listAdapter: AdAdapter
    private lateinit var itemPreviewAdapter: ItemPreviewAdapter
    private lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = FragmentDiscoverBinding.inflate(inflater, container, false)

        // only if have parameters
        viewModelFactory = DiscoverViewModelFactory(activity!!.application)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DiscoverViewModel::class.java)

        // init UI components
        dataBinding.viewmodel = viewModel
        listAdapter = AdAdapter(viewModel)
        itemPreviewAdapter = ItemPreviewAdapter()


        // when ads updated, update UI
        viewModel.adList.observe(this, Observer { storeResponse: StoreResponse<List<Ad>> ->

            when (storeResponse) {
                is StoreResponse.Error -> Timber.d(storeResponse.error)
                is StoreResponse.Data -> updateAdList(storeResponse.requireData())
            }
        })

        viewModel.popularItemsList.observe(this, Observer { storeResponse: StoreResponse<List<Item>> ->

            when (storeResponse) {
                is StoreResponse.Error -> Timber.d(storeResponse.error)
                is StoreResponse.Data -> updatePopularItemsList(storeResponse.requireData())
            }
        })

        return dataBinding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // bind resources
        toolbar = view.findViewById(
            R.id.toolbar)

        toolbar.title = "Discover"

        viewModel.refreshAds()
        viewModel.refreshPopularItems()

    }

    private fun updateAdList(data: List<Ad>) {

        if(ad_list.adapter==null)
            ad_list.adapter = listAdapter

        listAdapter.submitList(data)

    }

    private fun updatePopularItemsList(data: List<Item>) {

        if(popular_items_list.adapter ==null)
            popular_items_list.adapter = itemPreviewAdapter

        itemPreviewAdapter.submitList(data)

    }

}
