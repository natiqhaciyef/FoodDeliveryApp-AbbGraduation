package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.FoodModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.RecyclerFoodCardBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter.clickaction.SetOnCategorySelected
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home.HomeFragment
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home.HomeFragmentDirections
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel.HomeViewModel

class FoodAdapter(val mContext: Context, var list: MutableList<FoodModel>, val viewModel: HomeViewModel) :
    RecyclerView.Adapter<FoodAdapter.FoodHolder>() {

    inner class FoodHolder(val binding: RecyclerFoodCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodHolder {
        val binding: RecyclerFoodCardBinding = DataBindingUtil
            .inflate(LayoutInflater.from(mContext), R.layout.recycler_food_card, parent, false)
        return FoodHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodHolder, position: Int) {
        val itemView = holder.binding
        val food = list[position]
        var b = false

        itemView.foodModel = food
        itemView.foodIsLikedButton.setOnClickListener {
            b = !b
            if (b) {
                itemView.foodIsLikedButton.setImageResource(R.drawable.like_button_filled)
                viewModel.saveFoodModelFromDB(food)
            }else {
                itemView.foodIsLikedButton.setImageResource(R.drawable.like_button_empty)
                viewModel.deleteFoodModelFromDB(food)
            }
        }
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

    fun filter(list: MutableList<FoodModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}