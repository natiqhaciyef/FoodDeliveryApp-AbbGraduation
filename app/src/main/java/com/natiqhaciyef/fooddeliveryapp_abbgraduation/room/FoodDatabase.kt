package com.natiqhaciyef.fooddeliveryapp_abbgraduation.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.FoodModel

@Database(entities = [FoodModel::class], version = 1)
abstract class FoodDatabase : RoomDatabase(){
    abstract fun getDao(): FoodDao
}