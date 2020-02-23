package com.uniqlo.uniqloandroidapp.ui.cart

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dropbox.android.external.store4.ResponseOrigin
import com.dropbox.android.external.store4.StoreResponse
import com.dropbox.android.external.store4.fresh
import com.uniqlo.uniqloandroidapp.UniqloApplication
import com.uniqlo.uniqloandroidapp.database.CartItemEntity
import com.uniqlo.uniqloandroidapp.respository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(
    val app: Application
) : ViewModel() {

    private val cartItemStore = (app as UniqloApplication).cartItemsStore
    var cartRepository: CartRepository = CartRepository


    private val _cartItemList = MutableLiveData<StoreResponse<List<CartItemEntity>>>()
    val cartItemList: LiveData<StoreResponse<List<CartItemEntity>>>
        get() = _cartItemList

    private val _subtotal = MutableLiveData<Float>()
    val subtotal: LiveData<Float>
        get() = _subtotal

 private val _tax = MutableLiveData<Float>()
    val tax: LiveData<Float>
        get() = _tax

 private val _totalCost = MutableLiveData<Float>()
    val totalCost: LiveData<Float>
        get() = _totalCost


    fun refreshCartItemList() {
        viewModelScope.launch {
            _cartItemList.value = try {
                val data = cartItemStore.fresh("refresh")
                StoreResponse.Data(data, ResponseOrigin.Fetcher)

            } catch (e: Exception) {
                StoreResponse.Error(e, ResponseOrigin.Fetcher)
            }
        }
    }

    // update costs based on items in cart
    fun updateCost() {
        var newSubtotal = 0f
        var newTax = 0f
        var newTotal = 0f

        ((cartItemList.value)?.requireData()?: emptyList()).forEach {
            newSubtotal+= it.price * it.quantity
        }

        newTax = newSubtotal * 0.05f
        newTotal = newSubtotal + newTax

        _subtotal.value = newSubtotal
        _tax.value = newTax
        _totalCost.value = newTotal
    }

    fun removeItemFromCart(cartItemEntity: CartItemEntity) {

        viewModelScope.launch {
            cartRepository.removeItemFromCart(app, cartItemEntity)

        }
        refreshCartItemList()
    }

}