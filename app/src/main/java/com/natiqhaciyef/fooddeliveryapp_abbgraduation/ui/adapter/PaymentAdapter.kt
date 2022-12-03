package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.PaymentModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.RecyclerLastPaymentCardBinding

class PaymentAdapter(val mContext: Context,val list: List<PaymentModel>):
    RecyclerView.Adapter<PaymentAdapter.PaymentHolder>(){

        inner class PaymentHolder(val binding: RecyclerLastPaymentCardBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentHolder {
        val binding: RecyclerLastPaymentCardBinding = DataBindingUtil
            .inflate(LayoutInflater.from(mContext), R.layout.recycler_last_payment_card, parent, false)
        return PaymentHolder(binding)
    }

    override fun onBindViewHolder(holder: PaymentHolder, position: Int) {
        val itemView = holder.binding
        val payment = list[position]

        itemView.paymentModel = payment
    }

    override fun getItemCount(): Int = list.size
}