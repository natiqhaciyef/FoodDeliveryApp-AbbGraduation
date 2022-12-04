package com.natiqhaciyef.fooddeliveryapp_abbgraduation.network

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CRUDResponse
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CartResponse
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.FoodResponse
import retrofit2.http.*

interface FoodApi {
    //http://kasimadalan.pe.hu/foods/getAllFoods.php
    //http://kasimadalan.pe.hu/foods/insertFood.php
    //http://kasimadalan.pe.hu/foods/getFoodsCart.php
    //http://kasimadalan.pe.hu/foods/deleteFood.php

    @GET("foods/getAllFoods.php")
    suspend fun getAllFood(): FoodResponse

    @POST("foods/getFoodsCart.php")
    @FormUrlEncoded
    suspend fun getAllCart(@Field("userName") userName: String = "Natiq"): CartResponse

    @POST("foods/insertFood.php")
    @FormUrlEncoded
    suspend fun addToCart(
        @Field("name") name: String,
        @Field("image") image: String,
        @Field("price") price: Int,
        @Field("category") category: String,
        @Field("orderAmount") orderAmount: Int,
        @Field("userName") userName: String = "Natiq"
    ): CRUDResponse

    @POST("foods/deleteFood.php")
    @FormUrlEncoded
    suspend fun deleteFromCart(
        @Field("cartId") id: Int,
        @Field("userName") userName: String
    ): CRUDResponse
}