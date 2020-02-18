package com.uniqlo.uniqloandroidapp.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProviders

import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.ui.discover.DiscoverViewModel

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment() {

    private lateinit var viewModelFactory: CartViewModelFactory
    private lateinit var viewModel: CartViewModel

    private lateinit var toolbar: Toolbar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_cart, container, false)

        // only if have parameters
        viewModelFactory = CartViewModelFactory(activity!!.application)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CartViewModel::class.java)


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // bind resources
        toolbar = view.findViewById(
            R.id.toolbar)

        toolbar.title = "Cart"

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
//        (activity as AppCompatActivity).run { supportActionBar?.title = "Cart" }


    }

}
