package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CartOrderModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var repo: AppRepository) : ViewModel() {
    val cartLiveData = MutableLiveData<List<CartOrderModel>>()

    init {
        getAllCart("Natiq")
    }

    fun getAllCart(username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                cartLiveData.value = repo.getAllCart(username)

            } catch (e: Exception) {
                cartLiveData.value = listOf()
            }
        }
    }

    fun deleteCart(cartId: Int, username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            repo.deleteFromCart(cartId, username)
            getAllCart("Natiq")
        }
    }
}