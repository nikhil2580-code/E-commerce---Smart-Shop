package com.nikhilkhairnar.shopsmart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikhilkhairnar.shopsmart.adapters.AllOrdersAdapter
import com.nikhilkhairnar.shopsmart.databinding.FragmentOrdersBinding

class AllOrdersFragment: Fragment() {

    private lateinit var binding: FragmentOrdersBinding
    val orderAdapter by lazy { AllOrdersAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupOrdersRv()

        orderAdapter.onClick = {
            val action = AllOrdersFragmentDirections.actionAllOrdersFragmentToOrderDetailFragment(it)
            findNavController().navigate(action)

        }


    }

    private fun setupOrdersRv() {
        binding.rvAllOrders.apply {
            adapter = orderAdapter
            layoutManager  = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        }
    }

}