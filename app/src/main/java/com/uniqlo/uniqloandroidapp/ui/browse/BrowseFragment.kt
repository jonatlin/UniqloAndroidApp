package com.uniqlo.uniqloandroidapp.ui.browse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar

import com.uniqlo.uniqloandroidapp.R


class BrowseFragment : Fragment() {
//    private lateinit var viewDataBinding: FragmentSearchBinding

    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        setHasOptionsMenu(true)

        val view =  inflater.inflate(R.layout.fragment_browse, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // initialize views
        toolbar = view.findViewById(
            R.id.toolbar)
        toolbar.title = "Browse"



    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
//        (activity as AppCompatActivity).run { supportActionBar?.title = "Browse" }


    }

}
