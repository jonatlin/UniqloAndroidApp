package com.uniqlo.uniqloandroidapp.ui.discover


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.uniqlo.uniqloandroidapp.adapter.AdAdapter
import com.uniqlo.uniqloandroidapp.databinding.FragmentDiscoverBinding

/**
 * Shows ads to user
 */
class DiscoverFragment : Fragment() {

    private lateinit var dataBinding: FragmentDiscoverBinding
//    private lateinit var epoxyView: EpoxyRecyclerView
    private lateinit var viewModel: DiscoverViewModel
    private lateinit var viewModelFactory: DiscoverViewModelFactory


    private lateinit var listAdapter: AdAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = FragmentDiscoverBinding.inflate(inflater, container, false)

        viewModelFactory = DiscoverViewModelFactory()
        /*viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(DiscoverViewModel::class.java)*/
        viewModel = ViewModelProviders.of(this)
            .get(DiscoverViewModel::class.java)

        dataBinding.viewmodel = viewModel

        setHasOptionsMenu(true)

        return dataBinding.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupNavigationObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.updateAds()

    }

    private fun setupListAdapter() {
        val viewModel = dataBinding.viewmodel

        if (viewModel != null) {
            listAdapter =
                AdAdapter(viewModel)
            dataBinding.adList.adapter = listAdapter
        }

    }

    private fun setupNavigationObserver() {


    }


}
