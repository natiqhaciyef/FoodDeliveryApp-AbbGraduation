package com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class FoodModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id") var id: Int,
    @ColumnInfo("name") var name: String,
    @ColumnInfo("image") var image: String,
    @ColumnInfo("price") var price: Int,
    @ColumnInfo("category") var category: String
): Serializable