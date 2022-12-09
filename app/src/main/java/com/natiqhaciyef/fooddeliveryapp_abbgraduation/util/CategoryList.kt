package com.natiqhaciyef.fooddeliveryapp_abbgraduation.util

import android.content.Context
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CategoryModel
import kotlin.coroutines.coroutineContext

object CategoryList {
    val list = arrayListOf(
        CategoryModel(R.string.all_category,"all_meals_category"),
        CategoryModel(R.string.meal_category,"meat_category"),
        CategoryModel(R.string.dessert_category,"pastry_category"),
        CategoryModel(R.string.drink_category,"cold_drink_category")
    )
}