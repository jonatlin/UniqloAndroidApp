package com.uniqlo.uniqloandroidapp.ui.results


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs

import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.ui.discover.DiscoverViewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class ResultsFragment : Fragment() {

    private val args: ResultsFragmentArgs by navArgs()
    private lateinit var viewModel: ResultsViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this)
            .get(ResultsViewModel::class.java)

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // convert to non nullable
        if(args.adId!=null) {
            var id: String = args.adId!!
            viewModel.updateItems(id)
            Timber.d(id)
        }


    }


}