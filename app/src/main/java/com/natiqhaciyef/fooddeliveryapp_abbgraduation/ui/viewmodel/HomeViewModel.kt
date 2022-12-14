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
class HomeViewModel @Inject constructor(var repo: AppRepository) : ViewModel() {
    val liveData = MutableLiveData<MutableList<FoodModel>>()

    init {
        getAllFoods()
    }

    fun getAllFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            liveData.value = repo.getAllFood().toMutableList()
        }
    }

    fun categoryFilter(category: String, list: MutableList<FoodModel>): MutableList<FoodModel> {
        var customList = mutableListOf<FoodModel>()
        if (category.lowercase() != "all" && !category.isEmpty() && category.lowercase() != "" && category.lowercase() != "bütün") {
            if (category.lowercase() == "meals" || category.lowercase() == "yeməklər") {
                for (element in list) {
                    if (element.category.lowercase() == "meals")
                        customList.add(element)
                }
            } else if (category.lowercase() == "desserts" || category.lowercase() == "şirniyyatlar") {
                for (element in list) {
                    if (element.category.lowercase() == "desserts")
                        customList.add(element)
                }
            } else if (category.lowercase() == "drinks" || category.lowercase() == "içkilər") {
                for (element in list) {
                    if (element.category.lowercase() == "drinks")
                        customList.add(element)
                }
            }
        } else {
            customList = list
        }

        return customList
    }


    fun filterByName(name: String,list: MutableList<FoodModel>): MutableList<FoodModel>{
        val customList = mutableListOf<FoodModel>()
        for (element in list){
            if (element.name.lowercase().contains(name.lowercase()))
                customList.add(element)
        }
        return customList
    }

    fun saveFoodModelFromDB(foodModel: FoodModel) {
        CoroutineScope(Dispatchers.Main).launch {
            repo.saveFoodModel(foodModel)
        }
    }

    fun deleteFoodModelFromDB(foodModel: FoodModel) {
        CoroutineScope(Dispatchers.Main).launch {
            repo.deleteFoodModel(foodModel)
        }
    }
}