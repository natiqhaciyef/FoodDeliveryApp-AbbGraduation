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
class HomeViewModel @Inject constructor(var repo: AppRepository)  : ViewModel() {
    val liveData = MutableLiveData<MutableList<FoodModel>>()

    init {
        getAllFoods()
    }

    fun getAllFoods(){
        CoroutineScope(Dispatchers.Main).launch {
            liveData.value = repo.getAllFood().toMutableList()
        }
    }

    fun categoryFilter(category: String, list: MutableList<FoodModel>): MutableList<FoodModel> {
        var customList = mutableListOf<FoodModel>()
        if (category.lowercase() == "all" || category.isEmpty() || category.lowercase() == ""){
            customList = list
        }else{
            customList = list.filter {
                it.category.lowercase() == category.lowercase()
            } as MutableList<FoodModel>
        }

        return customList
    }

    fun saveFoodModel(foodModel:FoodModel){
        CoroutineScope(Dispatchers.Main).launch{
            repo.saveFoodModel(foodModel)
        }
    }

    fun deleteFoodModel(foodModel:FoodModel){
        CoroutineScope(Dispatchers.Main).launch{
            repo.deleteFoodModel(foodModel)
        }
    }
}