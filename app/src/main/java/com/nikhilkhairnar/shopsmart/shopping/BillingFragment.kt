package com.nikhilkhairnar.shopsmart.shopping

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nikhilkhairnar.shopsmart.Address
import com.nikhilkhairnar.shopsmart.CartProduct
import com.nikhilkhairnar.shopsmart.HorizontalItemDecoration
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.adapters.AddressAdapter
import com.nikhilkhairnar.shopsmart.adapters.BillingProductsAdapter
import com.nikhilkhairnar.shopsmart.databinding.FragmentBillingBinding
import com.nikhilkhairnar.shopsmart.order.Order
import com.nikhilkhairnar.shopsmart.order.OrderStatus

class BillingFragment: Fragment() {
  private lateinit var  binding: FragmentBillingBinding
  private val addressAdapter by lazy { AddressAdapter() }
    private val billingProductsAdapter by lazy { BillingProductsAdapter()}
    private var products = emptyList<CartProduct>()
    private var totalPrice = 0f
    private var order: Order? = null

//    private var selectedAddress: Address? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navBackStackEntry = findNavController().currentBackStackEntry
        val args = navBackStackEntry?.arguments?.let { BillingFragmentArgs.fromBundle(it) }
        args?.let {
            products = it.products.toList()
            totalPrice = it.totalPrice

            Log.d("BillingFragment", "Total Price: $totalPrice")
            Log.d("BillingFragment", "Products: $products")
        } ?: Log.e("BillingFragment", "Arguments not found")
    }





    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBillingBinding.inflate(inflater)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBillingProductsRv()
        setupAddressRv()

        binding.imageAddAddress.setOnClickListener {
            findNavController().navigate(R.id.action_billingFragment_to_addressFragment)
        }
        billingProductsAdapter.differ.submitList(products)

        totalPrice = calculateTotalPrice(products)
        binding.tvTotalPrice.text = "$$totalPrice"

//        addressAdapter.onClick = {
//            selectedAddress = it
//        }

        binding.buttonPlaceOrder.setOnClickListener {
//            if(selectedAddress == null) {
//                Toast.makeText(requireContext(), "Please select and address", Toast.LENGTH_SHORT)
//                    .show()
//                return@setOnClickListener
//            }
            showOrderConfirmationDialog()

        }



    }

    private fun calculateTotalPrice(products: List<CartProduct>): Float {
        var  totalPrice = 0f
        for(product in products){
            totalPrice += product.product.price.toFloat() * product.quantity
        }
        return totalPrice

    }




    private fun showOrderConfirmationDialog() {
        AlertDialog.Builder(context)
            .setTitle("Order item")
            .setMessage("Do you want to order you cart item")
            .setNegativeButton("Cancel"){dialog, _ ->
                dialog.dismiss()

            }
            .setPositiveButton("Yes") { dialog, _ ->
                order = Order(
                    OrderStatus.Ordered.status,
                   totalPrice,
                  products,
//                    selectedAddress!!
                )
                val action = BillingFragmentDirections.actionBillingFragmentToOrderDetailFragment(order!!)
                findNavController().navigate(action)
            }
            .show()
    }

    private fun setupAddressRv() {
        binding.rvAddress.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL,false)
            adapter = addressAdapter
            addItemDecoration(HorizontalItemDecoration())
        }
    }

    private fun setupBillingProductsRv() {
        binding.rvProducts.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL,false)
            adapter = billingProductsAdapter
            addItemDecoration(HorizontalItemDecoration())
        }
    }


}