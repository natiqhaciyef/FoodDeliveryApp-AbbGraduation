package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.CartOrderModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.FragmentCartBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter.CartAdapter
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding: FragmentCartBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private var list = listOf<CartOrderModel>()
    private lateinit var cartAdapter: CartAdapter
    private var username: String = ""
    private val viewModel: CartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cartFragment = this
        auth = Firebase.auth
        firestore = Firebase.firestore
        getUserName()

        viewModel.cartLiveData.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                list = it
                cartAdapter = CartAdapter(requireContext(), list, viewModel)
                binding.cartAdapter = cartAdapter
                binding.cartItemLessText.visibility = View.GONE
                binding.cartRecyclerView.visibility = View.VISIBLE
            } else {
                binding.cartItemLessText.visibility = View.VISIBLE
                binding.cartRecyclerView.visibility = View.GONE
            }
        }
    }

    private fun getUserName() {
        firestore.collection("UserNames").document(auth.currentUser!!.uid)
            .addSnapshotListener { value, error ->
                if (value != null) {
                    username += value.get("name") as String
                }
            }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllCart("Natiq")
    }
}