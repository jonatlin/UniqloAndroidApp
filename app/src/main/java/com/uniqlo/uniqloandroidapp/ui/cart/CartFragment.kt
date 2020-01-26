package com.uniqlo.uniqloandroidapp.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

import com.uniqlo.uniqloandroidapp.R

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment() {

    private lateinit var toolbar: Toolbar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // bind resources
        toolbar = view.findViewById(
            R.id.toolbar)

        toolbar.title = "Cart"

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).run { supportActionBar?.title = "Cart" }


    }

}
