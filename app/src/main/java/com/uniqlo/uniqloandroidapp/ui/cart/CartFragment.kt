package com.uniqlo.uniqloandroidapp.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.dropbox.android.external.store4.StoreResponse

import com.uniqlo.uniqloandroidapp.R
import com.uniqlo.uniqloandroidapp.adapter.CartItemAdapter
import com.uniqlo.uniqloandroidapp.database.CartItemEntity
import com.uniqlo.uniqloandroidapp.ui.discover.DiscoverViewModel
import kotlinx.android.synthetic.main.dialog_about.*
import kotlinx.android.synthetic.main.fragment_cart.*
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class CartFragment : Fragment() {

    private lateinit var viewModelFactory: CartViewModelFactory
    private lateinit var viewModel: CartViewModel

    private lateinit var toolbar: Toolbar
    private lateinit var cartItemAdapter: CartItemAdapter

    private lateinit var emptyView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_cart, container, false)

        // only if have parameters
        viewModelFactory = CartViewModelFactory(activity!!.application)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(CartViewModel::class.java)


        cartItemAdapter = CartItemAdapter(viewModel)


        viewModel.cartItemList.observe(
            this,
            Observer { storeResponse: StoreResponse<List<CartItemEntity>> ->

                when (storeResponse) {
                    is StoreResponse.Error -> {
                        Timber.d("cart item list error")
                    }
                    is StoreResponse.Data -> {
                        Timber.d(storeResponse.requireData().toString())
                        updateCartItemList(storeResponse.requireData())
                        viewModel.updateCost()
                    }
                }
            })

        viewModel.subtotal.observe(
            this,
            Observer { cost: Float ->
                subtotal_value.text = String.format("$%.2f", cost)
            })

        viewModel.tax.observe(
            this,
            Observer { cost: Float ->
                tax_value.text = String.format("$%.2f", cost)
            })

        viewModel.totalCost.observe(
            this,
            Observer { cost: Float ->
                total_value.text = String.format("$%.2f", cost)
            })

            return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // bind resources
        toolbar = view.findViewById(
            R.id.toolbar
        )
        emptyView = LayoutInflater.from(context).inflate(R.layout.empty_cart, coordinator_layout, false)

        cart_item_list.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))

        toolbar.title = "Cart"

        viewModel.refreshCartItemList()


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
//        (activity as AppCompatActivity).run { supportActionBar?.title = "Cart" }


    }

    fun updateCartItemList(data: List<CartItemEntity>) {

        if (cart_item_list.adapter == null)
            cart_item_list.adapter = cartItemAdapter

        if(data.size==0) {
            nested_scroll.visibility = View.GONE
            emptyView.visibility = View.VISIBLE
            coordinator_layout.addView(emptyView)
        } else {
            emptyView.visibility=View.GONE
            nested_scroll.visibility = View.VISIBLE

//            coordinator_layout.removeView(emptyView)
            cartItemAdapter.submitList(data)

        }

    }

}
