package com.uniqlo.uniqloandroidapp.ui.results


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager

import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.adapter.AdAdapter
import com.uniqlo.uniqloandroidapp.adapter.ItemAdapter
import com.uniqlo.uniqloandroidapp.databinding.FragmentDiscoverBinding
import com.uniqlo.uniqloandroidapp.databinding.FragmentResultsBinding
import com.uniqlo.uniqloandroidapp.ui.discover.DiscoverViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class ResultsFragment : Fragment() {

    private lateinit var dataBinding: FragmentResultsBinding
    private val args: ResultsFragmentArgs by navArgs()
    private lateinit var viewModel: ResultsViewModel

    private lateinit var listAdapter: ItemAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        dataBinding = FragmentResultsBinding.inflate(inflater, container, false)

        // set view model
        viewModel = ViewModelProviders.of(this)
            .get(ResultsViewModel::class.java)

        dataBinding.viewmodel = viewModel


        // set layouts
       /* val recyclerView = root.recyclerView
        val layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager*/

        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        dataBinding.lifecycleOwner = this.viewLifecycleOwner

        setupListAdapter()


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // convert to non nullable
        if(args.adId!=null) {
            var id: String = args.adId!!
            viewModel.updateResultItems(id)
            Timber.d(id)
        }


    }

    private fun setupListAdapter() {
        val viewModel = dataBinding.viewmodel

        if (viewModel != null) {
            listAdapter = ItemAdapter()
            dataBinding.recyclerView.adapter = listAdapter


        }
    }


}