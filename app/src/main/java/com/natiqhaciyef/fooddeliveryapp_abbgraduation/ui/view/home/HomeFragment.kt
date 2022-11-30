package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.data.model.FoodModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.FragmentHomeBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter.CategoryAdapter
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter.FoodAdapter
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel.HomeViewModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.util.CategoryList
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val categoryList = CategoryList.list
    private var foodList = listOf<FoodModel>()
    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeFragment = this
        binding.categoryAdapter = CategoryAdapter(requireContext(), categoryList)
        binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        viewModel.liveData.observe(viewLifecycleOwner, Observer{
            foodList = it
            binding.foodAdapter = FoodAdapter(requireContext(), foodList)
            binding.foodRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomeViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFoods()
    }
}