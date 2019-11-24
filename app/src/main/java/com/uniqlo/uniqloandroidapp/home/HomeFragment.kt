package com.uniqlo.uniqloandroidapp.home


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.uniqlo.uniqloandroidapp.R
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_appbar.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

//    private lateinit var binding: Home

            override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        (activity as AppCompatActivity).setActionBar(schedule_appbar.toolbar)

//        binding =

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        binding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupNavigationObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        schedule_appbar.toolbar.run {

            inflateMenu(R.menu.nav_top_menu)
            setOnMenuItemClickListener()
            { item ->
                if (item.itemId == R.id.search) {
                    Toast.makeText(context, "TODO: Create Search Dialog", Toast.LENGTH_SHORT).show()
                    true
                } else {
                    false
                }

            }

        }
    }

    private fun setupListAdapter() {


    }

    private fun setupNavigationObserver() {


    }


    }
