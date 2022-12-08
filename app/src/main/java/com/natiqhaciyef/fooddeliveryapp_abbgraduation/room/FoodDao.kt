package com.natiqhaciyef.fooddeliveryapp_abbgraduation.room

import androidx.room.*
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.FoodModel

@Dao
interface FoodDao {
    @Query("SELECT * FROM food_model")
    suspend fun loadFoodModelFromDb():List<FoodModel>

    @Insert
    suspend fun saveData(foodModel: FoodModel)

    @Delete
    suspend fun deleteData(foodModel: FoodModel)
}