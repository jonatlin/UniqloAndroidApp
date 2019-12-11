package com.uniqlo.uniqloandroidapp.discover


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.uniqlo.uniqloandroidapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_discover.*
import kotlinx.android.synthetic.main.main_appbar.view.*

/**
 * A simple [Fragment] subclass.
 */
class DiscoverFragment : Fragment() {

//    private lateinit var binding: Home

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        (activity as AppCompatActivity).setActionBar(schedule_appbar.toolbar)

//        binding =

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        binding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupNavigationObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



    }

    private fun setupListAdapter() {


    }

    private fun setupNavigationObserver() {


    }


}
