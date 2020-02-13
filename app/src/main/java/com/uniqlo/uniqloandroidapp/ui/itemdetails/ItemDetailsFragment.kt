package com.uniqlo.uniqloandroidapp.ui.itemdetails


import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dropbox.android.external.store4.StoreResponse

import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.data.Item
import com.uniqlo.uniqloandroidapp.ui.results.ResultsFragmentArgs
import kotlinx.android.synthetic.main.fragment_item_details.*
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.main_appbar.*
import kotlinx.android.synthetic.main.main_appbar.view.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class ItemDetailsFragment : Fragment() {

    private val args: ItemDetailsFragmentArgs by navArgs()

    private lateinit var viewModel: ItemDetailsViewModel
    private lateinit var viewModelFactory: ItemDetailsViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_item_details, container, false)

        viewModelFactory = ItemDetailsViewModelFactory(activity!!.application)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ItemDetailsViewModel::class.java)

        viewModel.item.observe(this, Observer { storeResponse: StoreResponse<Item> ->

            when (storeResponse) {
                is StoreResponse.Error -> {
                    updateItemDetails(Item(imageUrl="https://objectstorage.us-ashburn-1.oraclecloud.com/n/idi3qahxtzru/b/Uniqlo/o/STAR_WARS_FOREVER_UT_STASH_Blue.jpg"))
                    Timber.d(storeResponse.error)

                }
                is StoreResponse.Data -> {
                    val res = storeResponse.requireData()

                    updateItemDetails(res)
                    /*updateImage(res.imageUrl)
                    updateItemName(res.name)*/

                }
            }

        })

        if (args.itemId != null) {
            var id: String = args.itemId!!
            viewModel.updateItem(id)
            Timber.d("Item ID: %s", id)
        }


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {



        view.toolbar.navigationIcon =
            AppCompatResources.getDrawable(requireContext(), R.drawable.ic_arrow_back_primary_32dp)
        (view.toolbar as Toolbar).setNavigationOnClickListener(
            View.OnClickListener { findNavController().navigateUp() }
        )
    }

    private fun updateItemDetails(item: Item) {

        // app bar
        if (toolbar != null)
            toolbar.title = item.name

        // image
        if (item_image != null) {
            Glide.with(view!!.context)
                .load(item.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .placeholder(R.color.colorPrimaryLight)
                .into(item_image)
        }

        // name
        if (item_name != null) {
            item_name.text = item.name
        }
        // price
        if (item_price != null) {
            item_price.text = String.format("$%.2f", item.originalPrice)
        }

        // name
        if (item_description != null) {
            item_description.text = item.description
        }
    }


    }
