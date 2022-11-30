package com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model

import java.io.Serializable

data class FoodModel(
    var id: Int,
    var name: String,
    var image: String,
    var price: Int,
    var category: String
): Serializable