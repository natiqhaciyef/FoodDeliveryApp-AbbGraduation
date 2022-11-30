package com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model

data class CartOrderModel(
    var id: Int,
    var name: String,
    var image: String,
    var price: Int,
    var category: String,
    var orderAmount: Int,
    var username: String
)