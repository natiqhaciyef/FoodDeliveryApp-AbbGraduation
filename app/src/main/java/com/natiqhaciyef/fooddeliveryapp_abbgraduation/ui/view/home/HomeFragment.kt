package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
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
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter.clickaction.SetOnCategorySelected
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel.HomeViewModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.util.CategoryList
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val categoryList = CategoryList.list
    private var foodList = mutableListOf<FoodModel>()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var foodAdapter: FoodAdapter
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

        (activity as MainActivity).toolbarInclude.visibility = View.VISIBLE

        viewModel.liveData.observe(viewLifecycleOwner,){
            foodList = it

            foodAdapter = FoodAdapter(requireContext(), foodList, viewModel)
            binding.foodAdapter = foodAdapter
            binding.foodRecyclerView.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

            categoryAdapter = CategoryAdapter(requireContext(), categoryList)
            binding.categoryAdapter = categoryAdapter
            categoryAdapter.itemClick(object: SetOnCategorySelected{
                override fun categorySelected(category: String) {
                    filterByCategory(category)
                }
            })

            binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }

        binding.searchBarHomeFragment.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { filterByName(newText.lowercase()) }
                return false
            }

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

    fun filterByCategory(category: String){
        val list = viewModel.categoryFilter(category, foodList)
        if (list.isNotEmpty()){
            foodAdapter.filter(list)
        }
    }

    fun filterByName(name: String){
        val list = viewModel.filterByName(name, foodList)
        if (list.isEmpty())
            Toast.makeText(requireContext(), "Post not found...", Toast.LENGTH_LONG).show()
        else
            foodAdapter.filter(list)
    }
}