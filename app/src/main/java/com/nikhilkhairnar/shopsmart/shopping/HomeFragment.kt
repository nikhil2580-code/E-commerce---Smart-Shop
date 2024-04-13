package com.nikhilkhairnar.shopsmart.shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.adapters.HomeViewpageAdapter
import com.nikhilkhairnar.shopsmart.databinding.FragmentHomeBinding
import com.nikhilkhairnar.shopsmart.fragments.categories.CameraFragment
import com.nikhilkhairnar.shopsmart.fragments.categories.ComputerFragment
import com.nikhilkhairnar.shopsmart.fragments.categories.FurnitureFragment
import com.nikhilkhairnar.shopsmart.fragments.categories.MainCategoryFragment
import com.nikhilkhairnar.shopsmart.fragments.categories.SmartphoneFragment
import com.nikhilkhairnar.shopsmart.fragments.categories.ShoesFragment



class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val categoriesFragment = arrayListOf(
            MainCategoryFragment(),
            CameraFragment(),
            FurnitureFragment(),
            ComputerFragment(),
            SmartphoneFragment(),
            ShoesFragment()
        )

        binding.viewPageHome.isUserInputEnabled = false

        val viewpageAdapter = HomeViewpageAdapter(categoriesFragment, childFragmentManager, lifecycle)
        binding.viewPageHome.adapter = viewpageAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPageHome) { tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Camera"
                2 -> tab.text = "Furniture"
                3 -> tab.text = "Computer"
                4 -> tab.text = "Smartphone"
                5 -> tab.text = "Shoes"
            }
        }.attach()
    }

    companion object {
        fun newInstance() = HomeFragment()

    }
}
