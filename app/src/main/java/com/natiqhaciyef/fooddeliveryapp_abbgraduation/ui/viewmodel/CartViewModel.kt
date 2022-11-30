package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var repo: AppRepository): ViewModel() {

}