package com.uniqlo.uniqloandroidapp.ui.discover


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.appbar.AppBarLayout
import com.uniqlo.uniqloandroidapp.MainActivity
import com.uniqlo.uniqloandroidapp.R

import com.uniqlo.uniqloandroidapp.adapter.AdAdapter
import com.uniqlo.uniqloandroidapp.databinding.FragmentDiscoverBinding
import kotlinx.android.synthetic.main.fragment_discover.view.*
import kotlinx.android.synthetic.main.main_appbar.*
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
    private lateinit var toolbar: Toolbar



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = FragmentDiscoverBinding.inflate(inflater, container, false)

        // only if have parameters
//        viewModelFactory = DiscoverViewModelFactory()
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

        // use activity viewmodel instead?

        /*(activity as AppCompatActivity).run {
            setSupportActionBar(toolbar)
            supportActionBar?.title = "Discover"}*/

        dataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // bind resources
        /*toolbar = view!!.findViewById(
            R.id.discover_action_bar)

        toolbar.title = "Discover"*/

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

}
