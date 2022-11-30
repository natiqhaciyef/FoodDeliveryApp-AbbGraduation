package com.natiqhaciyef.fooddeliveryapp_abbgraduation.di

import com.natiqhaciyef.fooddeliveryapp_abbgraduation.network.ApiUtil
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.network.FoodApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFoodApi(): FoodApi{
        return ApiUtil.getApi()
    }


}