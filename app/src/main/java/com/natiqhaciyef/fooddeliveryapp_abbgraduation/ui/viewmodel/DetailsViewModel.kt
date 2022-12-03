package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CartOrderModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(var repo: AppRepository) : ViewModel() {
    fun addToCart(cartOrderModel: CartOrderModel){
        CoroutineScope(Dispatchers.Main).launch {
            repo.addToCart(
                cartOrderModel.name,
                cartOrderModel.image,
                cartOrderModel.price,
                cartOrderModel.category,
                cartOrderModel.orderAmount,
                cartOrderModel.userName
            )
            println("A")
        }
    }
}