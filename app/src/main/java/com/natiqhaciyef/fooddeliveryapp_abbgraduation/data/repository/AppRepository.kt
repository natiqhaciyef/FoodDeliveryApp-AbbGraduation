package com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.repository

import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.source.AppDataSource

class AppRepository(var dataSource: AppDataSource) {
    suspend fun getAllFood() = dataSource.getAllFood()

    suspend fun getAllCart(userName: String) = dataSource.getAllCart(userName)

    suspend fun deleteFromCart(cartId: Int, userName: String) =
        dataSource.deleteFromCart(cartId, userName)

    suspend fun addToCart(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        userName: String
    ) = dataSource.addToCart(name, image, price, category, orderAmount, userName)
}