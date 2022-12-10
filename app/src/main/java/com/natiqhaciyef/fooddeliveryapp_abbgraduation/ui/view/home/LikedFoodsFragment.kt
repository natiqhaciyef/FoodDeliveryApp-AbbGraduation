package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.FragmentLikedFoodsBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter.FoodAdapter
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.adapter.SavedFoodAdapter
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel.HomeViewModel
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.viewmodel.LikedPostsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class LikedFoodsFragment : Fragment() {
    private lateinit var binding: FragmentLikedFoodsBinding
    private val viewModel: LikedPostsViewModel by viewModels()
    private lateinit var adapter: SavedFoodAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_liked_foods, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.likeFoodsFragment = this

        (activity as MainActivity).toolbarInclude.visibility = View.GONE
        viewModel.savedPosts.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                adapter = SavedFoodAdapter(requireContext(), it, viewModel)
                binding.adapter = adapter
                binding.recyclerSavedFoods.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            }else{
                binding.saveItemLessText.visibility = View.VISIBLE
                binding.recyclerSavedFoods.visibility = View.GONE
            }
        }
    }
}