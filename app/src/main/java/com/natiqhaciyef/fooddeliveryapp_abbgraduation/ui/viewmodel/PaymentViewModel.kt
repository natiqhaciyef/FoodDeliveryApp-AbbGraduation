package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(var repo: AppRepository): ViewModel() {


}