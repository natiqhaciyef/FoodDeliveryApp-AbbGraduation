package com.natiqhaciyef.fooddeliveryapp_abbgraduation.util

import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CategoryModel

object CategoryList {
    val list = arrayListOf(
        CategoryModel("All","all_meals_category"),
        CategoryModel("Hot Meals","meat_category"),
        CategoryModel("Deserts","pastry_category"),
        CategoryModel("Drinks","cold_drink_category")
    )
}