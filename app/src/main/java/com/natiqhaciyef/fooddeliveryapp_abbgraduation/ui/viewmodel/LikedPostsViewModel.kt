package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.FoodModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikedPostsViewModel @Inject constructor(
    var repo: AppRepository
) : ViewModel() {
    val savedPosts = MutableLiveData<List<FoodModel>>()

    init {
        loadSavedFoods()
    }

    private fun loadSavedFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            savedPosts.value = repo.loadFoodModelFromDB()
        }
    }
}