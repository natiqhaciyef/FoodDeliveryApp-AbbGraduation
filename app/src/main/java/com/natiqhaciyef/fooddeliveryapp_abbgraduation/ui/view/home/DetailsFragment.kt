package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
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
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel.CartViewModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private var username: String = ""
    private val viewModel: DetailsViewModel by viewModels()
    private val cartViewModel: CartViewModel by viewModels()
    private val deletedList = mutableListOf<CartOrderModel>()
    private var observeList = listOf<CartOrderModel>()


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
        val foodModel = data.foodDetailsObject
        binding.foodModel = foodModel

        Glide.with(requireContext())
            .load("http://kasimadalan.pe.hu/foods/images/${data.foodDetailsObject.image}")
            .into(binding.detailsImageView)
        binding.totalPriceTextDetailsFragment.text =
            "Total price: ${
                binding.itemCountTextDetailsFragment.text.toString()
                    .toInt() * data.foodDetailsObject.price
            } $"

        binding.itemAddDetailsFragment.setOnClickListener {
            increaseAmount(data.foodDetailsObject)
        }

        binding.itemRemoveDetailsFragment.setOnClickListener {
            decreaseAmount(data.foodDetailsObject)
        }

        cartViewModel.getAllCart("Natiq")
        cartViewModel.cartLiveData.observe(viewLifecycleOwner) {
            observeList = it
            binding.addToCartButtonDetails.setOnClickListener { view ->
                getUserName()
                val list = listOf(
                    CartOrderModel(
                        cartId = 0,
                        name = binding.mealNameTextDetailsFragment.text.toString(),
                        image = "${foodModel.image}",
                        price = binding.itemCountTextDetailsFragment.text.toString()
                            .toInt() * foodModel.price,
                        category = foodModel.category,
                        orderAmount = binding.itemCountTextDetailsFragment.text.toString().toInt(),
                        userName = "Natiq"
                    )
                )

                for (element in observeList) {
                    // eyni add olani tapmaq
                    if (element.name == list[0].name) {
                        list[0].price += element.price
                        list[0].orderAmount += element.orderAmount

                        deletedList.add(element)
                    }
                }
                viewModel.addToCart(list[0])

                for (el in deletedList) {
                    cartViewModel.deleteCart(el.cartId, "Natiq")
                }
            }
        }
    }

    private fun increaseAmount(foodModel: FoodModel) {
        val count = binding.itemCountTextDetailsFragment.text.toString().toInt()
        binding.itemCountTextDetailsFragment.text = "${count + 1}"
        binding.totalPriceTextDetailsFragment.text =
            "Total price: ${
                binding.itemCountTextDetailsFragment.text.toString().toInt() * foodModel.price
            } $"
    }

    private fun decreaseAmount(foodModel: FoodModel) {
        val count = binding.itemCountTextDetailsFragment.text.toString().toInt()
        if (count > 1)
            binding.itemCountTextDetailsFragment.text = "${count - 1}"
        binding.totalPriceTextDetailsFragment.text =
            "Total price: ${
                binding.itemCountTextDetailsFragment.text.toString().toInt() * foodModel.price
            } $"
    }

    private fun getUserName() {
        firestore.collection("UserNames").document(auth.currentUser!!.uid)
            .addSnapshotListener { value, error ->
                if (value != null)
                    username += value.get("name") as String
            }
    }
}