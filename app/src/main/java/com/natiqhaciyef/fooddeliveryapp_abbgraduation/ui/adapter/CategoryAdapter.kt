package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CategoryModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.RecyclerCategoriesCardBinding

class CategoryAdapter(var mContext: Context, val list: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    inner class CategoryHolder(val binding: RecyclerCategoriesCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding: RecyclerCategoriesCardBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.recycler_categories_card, parent, false)
        return CategoryHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        val itemView = holder.binding
        val category = list[position]

        itemView.category = category
        itemView.categoryImageView.setImageResource(mContext.resources.getIdentifier(
            category.image,
            "drawable",
            mContext.packageName
        ))
    }

    override fun getItemCount(): Int = list.size
}