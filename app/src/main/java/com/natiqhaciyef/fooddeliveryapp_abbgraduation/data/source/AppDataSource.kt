package com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.source

import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CartOrderModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.FoodModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.network.FoodApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDataSource (var api: FoodApi) {

    suspend fun getAllFood(): List<FoodModel> =
        withContext(Dispatchers.IO) {
            api.getAllFood().foods
        }

    suspend fun getAllCart(userName: String): List<CartOrderModel> = withContext(Dispatchers.IO) {
        api.getAllCart(userName).list
    }

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