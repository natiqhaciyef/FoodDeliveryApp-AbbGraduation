package com.natiqhaciyef.fooddeliveryapp_abbgraduation.di

import android.content.Context
import androidx.room.Room
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.repository.AppRepository
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.source.AppDataSource
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.network.ApiUtil
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.network.FoodApi
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.room.FoodDao
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.room.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideDataSource(api: FoodApi, db: FoodDao): AppDataSource {
        return AppDataSource(api, db)
    }

    @Provides
    @Singleton
    fun provideRepository(ds: AppDataSource): AppRepository {
        return AppRepository(ds)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, FoodDatabase::class.java, "FoodModel").build().getDao()
}