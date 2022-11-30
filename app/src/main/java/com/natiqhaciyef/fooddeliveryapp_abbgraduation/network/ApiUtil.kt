package com.natiqhaciyef.fooddeliveryapp_abbgraduation.network

class ApiUtil {
    companion object{
        const val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getApi(): FoodApi = RetrofitClient.getClient(BASE_URL).create(FoodApi::class.java)
    }
}