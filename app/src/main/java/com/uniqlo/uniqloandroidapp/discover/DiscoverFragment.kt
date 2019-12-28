package com.uniqlo.uniqloandroidapp.discover


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.airbnb.epoxy.EpoxyRecyclerView
import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.databinding.FragmentDiscoverBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.android.synthetic.main.main_appbar.view.*

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
        viewModel = ViewModelProviders.of(this, viewModelFactory)
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



    }

    private fun setupListAdapter() {
        val viewModel = dataBinding.viewmodel

        if (viewModel != null) {
            listAdapter = AdAdapter(viewModel)
            dataBinding.adList.adapter = listAdapter
        }

    }

    private fun setupNavigationObserver() {


    }


}
