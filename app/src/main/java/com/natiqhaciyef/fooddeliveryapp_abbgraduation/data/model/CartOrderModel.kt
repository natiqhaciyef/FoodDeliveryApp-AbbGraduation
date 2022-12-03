package com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model

import java.io.Serializable

data class CartOrderModel(
    var cartId: Int,
    var name: String,
    var image: String,
    var price: Int,
    var category: String,
    var orderAmount: Int,
    var userName: String
): Serializable