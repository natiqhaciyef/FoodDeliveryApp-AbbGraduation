package com.natiqhaciyef.fooddeliveryapp_abbgraduation.di

import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.repository.AppRepository
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.source.AppDataSource
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.network.ApiUtil
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.network.FoodApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import javax.sql.DataSource

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideFoodApi(): FoodApi {
        return ApiUtil.getApi()
    }

    @Provides
    @Singleton
    fun provideDataSource(api: FoodApi): AppDataSource {
        return AppDataSource(api)
    }

    @Provides
    @Singleton
    fun provideRepository(ds: AppDataSource): AppRepository {
        return AppRepository(ds)
    }
}