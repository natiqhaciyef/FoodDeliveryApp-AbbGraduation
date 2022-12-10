package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.PaymentModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.FragmentPaymentBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter.PaymentAdapter
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.util.LastBalance
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.util.PaymentList
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.util.TotalData
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.util.filterInt
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.paymentFragment = this
        binding.lastBalance = LastBalance.lastBalance.toString()
        (activity as MainActivity).toolbarInclude.visibility = View.GONE
        if (TotalData.totalPrice != 0) {
            PaymentList.list.add(PaymentModel(TotalData.name, TotalData.totalPrice))
            val count = LastBalance.lastBalance - TotalData.totalPrice
            if (count >= 0) {
                binding.lastBalance = count.toString()
                LastBalance.lastBalance = count
                TotalData.name = ""
                TotalData.totalPrice = 0
            }else
                Toast.makeText(requireContext(), "Balance is not enough", Toast.LENGTH_SHORT).show()
        }
        binding.adapter = PaymentAdapter(requireContext(), PaymentList.list.reversed())

    }
}