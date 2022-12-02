package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CartOrderModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.FoodModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data: DetailsFragmentArgs by navArgs()
        auth = Firebase.auth
        firestore = Firebase.firestore

        binding.detailsFragment = this
        binding.foodModel = data.foodDetailsObject
        Glide.with(requireContext())
            .load("http://kasimadalan.pe.hu/foods/images/${data.foodDetailsObject.image}")
            .into(binding.detailsImageView)
        binding.totalPriceTextDetailsFragment.text =
            "Total price: ${binding.itemCountTextDetailsFragment.text.toString().toInt() * data.foodDetailsObject.price} $"

        binding.itemAddDetailsFragment.setOnClickListener {
            increaseAmount(data.foodDetailsObject)
        }

        binding.itemRemoveDetailsFragment.setOnClickListener {
            decreaseAmount(data.foodDetailsObject)
        }

        getUserName()

        binding.addToCartButtonDetails.setOnClickListener {
            val cartOrderModel = CartOrderModel(
                id = data.foodDetailsObject.id,
                name = data.foodDetailsObject.name,
                image = "http://kasimadalan.pe.hu/foods/images/${data.foodDetailsObject.image}",
                price = data.foodDetailsObject.price,
                category = data.foodDetailsObject.category,
                orderAmount = binding.itemCountTextDetailsFragment.text.toString().toInt(),
                username = username
            )
            val action = DetailsFragmentDirections.actionDetailsFragmentToCartFragment(cartOrderModel)
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun increaseAmount(foodModel: FoodModel) {
        val count = binding.itemCountTextDetailsFragment.text.toString().toInt()
        binding.itemCountTextDetailsFragment.text = "${count + 1}"
        binding.totalPriceTextDetailsFragment.text =
            "Total price: ${binding.itemCountTextDetailsFragment.text.toString().toInt() * foodModel.price} $"
    }

    private fun decreaseAmount(foodModel: FoodModel) {
        val count = binding.itemCountTextDetailsFragment.text.toString().toInt()
        if (count > 1)
            binding.itemCountTextDetailsFragment.text = "${count - 1}"
        binding.totalPriceTextDetailsFragment.text =
            "Total price: ${binding.itemCountTextDetailsFragment.text.toString().toInt() * foodModel.price} $"
    }

    private fun getUserName(){
        firestore.collection("UserNames").document(auth.currentUser!!.uid).addSnapshotListener{value, error ->
            if (value != null)
                username = value.get("name") as String
        }
    }
}