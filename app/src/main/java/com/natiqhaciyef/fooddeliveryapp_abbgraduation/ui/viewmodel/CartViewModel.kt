package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CartOrderModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var repo: AppRepository): ViewModel() {
    val cartLiveData = MutableLiveData<List<CartOrderModel>>()
    val username = "Natiq"
    init {
        getAllCart("Natiq")
    }

    fun getAllCart(username: String){
        CoroutineScope(Dispatchers.Main).launch {
            println(repo.getAllCart(username))
            cartLiveData.value = repo.getAllCart(username)
        }
    }

    fun deleteCart(cartId: Int, username: String){
        CoroutineScope(Dispatchers.Main).launch {
            repo.deleteFromCart(cartId,username)
            getAllCart(username)
        }
    }
}