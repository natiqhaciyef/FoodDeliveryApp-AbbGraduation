package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CartOrderModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.RecyclerCartItemCardBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel.CartViewModel

class CartAdapter(val mContext: Context, val list: List<CartOrderModel>, val viewModel: CartViewModel) : RecyclerView.Adapter<CartAdapter.CartHolder>() {

    inner class CartHolder(val binding: RecyclerCartItemCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartHolder {
        val binding: RecyclerCartItemCardBinding =
            DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.recycler_cart_item_card, parent, false)
        return CartHolder(binding)
    }

    override fun onBindViewHolder(holder: CartHolder, position: Int) {
        val itemView = holder.binding
        val cartOrder = list[position]
        itemView.cartOrder = cartOrder
        Glide.with(mContext).load("http://kasimadalan.pe.hu/foods/images/${cartOrder.image}").into(itemView.cartItemImageView)

        itemView.removeCartItem.setOnClickListener {
            viewModel.deleteCart(cartOrder.cartId, "Natiq")
        }
    }

    override fun getItemCount(): Int = list.size
}