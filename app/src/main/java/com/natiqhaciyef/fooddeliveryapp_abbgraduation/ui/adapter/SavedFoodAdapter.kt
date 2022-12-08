package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.FoodModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.RecyclerFoodCardBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home.HomeFragmentDirections

class SavedFoodAdapter(val mContext: Context, var list: List<FoodModel>) :
    RecyclerView.Adapter<SavedFoodAdapter.SavedFoodHolder>() {

    inner class SavedFoodHolder(val binding: RecyclerFoodCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedFoodHolder {
        val binding: RecyclerFoodCardBinding = DataBindingUtil
            .inflate(LayoutInflater.from(mContext), R.layout.recycler_food_card, parent, false)
        return SavedFoodHolder(binding)
    }

    override fun onBindViewHolder(holder: SavedFoodHolder, position: Int) {
        val itemView = holder.binding
        val food = list[position]

        //http://kasimadalan.pe.hu/foods/images/meatball.png
        itemView.foodModel = food
        itemView.foodIsLikedButton.visibility = View.INVISIBLE
        Glide.with(mContext).load("http://kasimadalan.pe.hu/foods/images/${food.image}")
            .into(itemView.foodRecyclerImage)
        holder.itemView.setOnClickListener {
            try {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(food)
                Navigation.findNavController(it).navigate(action)
            } catch (e: Exception) {
            }
        }
    }

    override fun getItemCount(): Int = list.size

}