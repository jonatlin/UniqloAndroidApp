package com.uniqlo.uniqloandroidapp.ui.discover


import android.opengl.Visibility
import android.os.Bundle
import android.view.*
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
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
//import com.uniqlo.uniqloandroidapp.databinding.FragmentDiscoverBinding
import kotlinx.android.synthetic.main.fragment_discover.*
import timber.log.Timber

/**
 * Shows ads to user
 */
class DiscoverFragment : Fragment() {

    //    private lateinit var dataBinding: FragmentDiscoverBinding
//    private lateinit var epoxyView: EpoxyRecyclerView
    private lateinit var viewModelFactory: DiscoverViewModelFactory
    private lateinit var viewModel: DiscoverViewModel

    private lateinit var itemPreviewAdapter: ItemPreviewAdapter
    private lateinit var adAdapter: AdAdapter
    private lateinit var toolbar: Toolbar
    private lateinit var nestedScroll: NestedScrollView
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        dataBinding = FragmentDiscoverBinding.inflate(inflater, container, false)
        val root = inflater.inflate(R.layout.fragment_discover, container, false)

        // only if have parameters
        viewModelFactory = DiscoverViewModelFactory(activity!!.application)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DiscoverViewModel::class.java)

        // init UI components
//        dataBinding.viewmodel = viewModel

        adAdapter = AdAdapter()
        itemPreviewAdapter = ItemPreviewAdapter()


        // when ads updated, update UI
        viewModel.adList.observe(this, Observer { storeResponse: StoreResponse<List<Ad>> ->

            when (storeResponse) {
                is StoreResponse.Error -> {
                    // default values if no backend
                    updateAdList(mutableListOf(Ad(imageUrl="https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_MARIMEKKO_MERINO-BLEND_PANTS_Brown.jpg"),
                        Ad(imageUrl="https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_2-WAY_STRETCH.jpg"),
                        Ad(imageUrl="https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_MAGIC_FOR_ALL_ICONS.jpg"),
                        Ad(imageUrl="https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/AD_ROGER_FEDERER.jpg")))
                    Timber.d(storeResponse.error)
                }
                is StoreResponse.Data -> updateAdList(storeResponse.requireData())
            }
        })

        viewModel.popularItemsList.observe(
            this,
            Observer { storeResponse: StoreResponse<List<Item>> ->

                when (storeResponse) {
                    is StoreResponse.Error -> {
                        // default values if no backend
                        updatePopularItemsList(
                            mutableListOf(
                                Item(imageUrl = "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/WOMEN_CASHMERE_CREW_NECK_SWEATER_Gray.jpg"),
                                Item(imageUrl = "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/WOMEN_FLEECE_LONG-SLEEVE_SET_Pink.jpg"),
                                Item(imageUrl = "https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/WOMEN_HEATTECH_WARM-LINED_PANTS_Gray.jpg")
                            )
                        )

                        Timber.d(storeResponse.error)
                    }
                    is StoreResponse.Data -> updatePopularItemsList(storeResponse.requireData())
                }
            })

        // TODO: fix this. hide views until ready
        viewModel.isLoading.observe(
            this,
            Observer {
                isLoading ->
                if(isLoading) {
                    nestedScroll.visibility=View.INVISIBLE
                    progressBar.visibility= View.VISIBLE
                    Timber.d("loading")
                } else {
                    nestedScroll.visibility=View.VISIBLE
                    progressBar.visibility= View.INVISIBLE
                    Timber.d("not loading")
                }

            }

        )

//        return dataBinding.root
        return root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        dataBinding.lifecycleOwner = this.viewLifecycleOwner
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // bind resources
        toolbar = view.findViewById(
            R.id.toolbar
        )
        nestedScroll = view.findViewById(R.id.nested_scroll)
        progressBar = view.findViewById(R.id.progress_bar)

        toolbar.title = "Discover"
        toolbar.inflateMenu(R.menu.discover_menu)
        toolbar.setOnMenuItemClickListener(

            Toolbar.OnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_refresh -> {
                        viewModel.refreshPopularItems(true)
                        viewModel.refreshAds(true)
                        true
                    }

                    else -> {
                        Timber.d("")
                        true
                    }

                }
            }
        )


        viewModel.refreshAds()
        viewModel.refreshPopularItems()

    }


    private fun updateAdList(data: List<Ad>) {

        if (ad_list.adapter == null)
            ad_list.adapter = adAdapter

        adAdapter.submitList(data)

    }

    private fun updatePopularItemsList(data: List<Item>) {

        if (popular_items_list.adapter == null)
            popular_items_list.adapter = itemPreviewAdapter

        itemPreviewAdapter.submitList(data)

    }

}
