package com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.source

import com.natiqhaciyef.fooddeliveryapp_abbgraduation.network.FoodApi

class AppDataSource(var api: FoodApi) {

    suspend fun getAllFood() = api.getAllFood()

    suspend fun getAllCart(userName: String) = api.getAllCart(userName)

    suspend fun deleteFromCart(cartId: Int, userName: String) = api.deleteFromCart(cartId, userName)

    suspend fun addToCart(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        userName: String
    ) = api.addToCart(
        name = name,
        image = image,
        price = price,
        category = category,
        orderAmount = orderAmount,
        userName = userName
    )
}