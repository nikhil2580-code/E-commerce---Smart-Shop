package com.nikhilkhairnar.shopsmart.shopping

import com.nikhilkhairnar.shopsmart.CartProduct
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nikhilkhairnar.shopsmart.CartDatabaseHelper
import com.nikhilkhairnar.shopsmart.Product

import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.adapters.CartProductAdapter
import com.nikhilkhairnar.shopsmart.databinding.FragmentCartBinding


class CartFragment : Fragment(R.layout.fragment_cart), CartProductAdapter.CartAdapterListener {
    private lateinit var binding: FragmentCartBinding
    private val cartAdapter by lazy { CartProductAdapter() }
    private val sharedPreferencesKey = "CART_PRODUCTS"
    private var productPrices: Map<Int, Float> = emptyMap()
    private lateinit var cartDatabaseHelper: CartDatabaseHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartDatabaseHelper = CartDatabaseHelper(requireContext())
        val saveProducts =  cartDatabaseHelper.getAllCartProducts()
        cartAdapter.submitList(saveProducts)
        updateTotalPrice(saveProducts)

        var totalPrice = 0f

        loadProductPricesFromSharedPreferences { productPrices ->
            totalPrice = calculateTotalPrice(cartAdapter.differ.currentList, productPrices)
            binding.totalPriceTv.text = "$${String.format("%.2f", totalPrice)}"
        }


        parentFragmentManager.setFragmentResultListener(
            "productAdded",
            viewLifecycleOwner,
            FragmentResultListener { _, result ->
                val addedProduct = result.getSerializable("addedProduct") as Product

                val cartProduct = CartProduct(addedProduct, 1)
                cartDatabaseHelper.insertCartProduct(cartProduct)

                // Check if the product is already in the cart
                val existingProduct = cartAdapter.differ.currentList.firstOrNull { it.product.id == addedProduct.id }

                if (existingProduct != null) {
                    // Product is already in the cart, increase its quantity
                    val updatedProducts = ArrayList(cartAdapter.differ.currentList)
                    val index = updatedProducts.indexOf(existingProduct)
                    updatedProducts[index] = existingProduct.copy(quantity = existingProduct.quantity + 1)
                    cartAdapter.submitList(updatedProducts)


                } else {
                    val updatedProducts = ArrayList(cartAdapter.differ.currentList)
                    updatedProducts.add(cartProduct)
                    cartAdapter.submitList(updatedProducts)
                    val insertId = cartDatabaseHelper.insertCartProduct(cartProduct)

                }

                saveProductsToSharedPreferences(cartAdapter.differ.currentList)
                updateTotalPrice(cartAdapter.differ.currentList)

                totalPrice = calculateTotalPrice(cartAdapter.differ.currentList, productPrices)
                binding.totalPriceTv.text = "$${String.format("%.2f", totalPrice)}"
            }
        )


        setupCartRv()

        cartAdapter.onProductClick = {
            val b = Bundle().apply { putSerializable("product", it.product) }
            findNavController().navigate(R.id.action_cartFragment_to_productDetailsFragment, b)
        }

        binding.btnCheckOut.setOnClickListener {
            if (cartAdapter.differ.currentList.isEmpty()) {
                showEmptyCart()
                hideOtherView()
            } else {
                clearCartDatFromSharedPreferences()
                val action = CartFragmentDirections.actionCartFragmentToBillingFragment(totalPrice, cartAdapter.differ.currentList.toTypedArray())
                findNavController().navigate(action)
            }
        }


        cartAdapter.onPlusClick = { cartProduct ->
            // Handle the plus button click event here
            cartAdapter.increaseQuantity(cartProduct)
            updateTotalPrice(cartAdapter.differ.currentList)
            saveProductsToSharedPreferences(cartAdapter.differ.currentList)
            cartDatabaseHelper.updateCartProductQuantity(cartProduct.product.id, cartProduct.quantity)
        }

        cartAdapter.onMinusClick = { cartProduct ->
            // Handle the minus button click event here
            val position = cartAdapter.differ.currentList.indexOf(cartProduct)
            if (position != -1) {
                val holder = binding.recyclerViewCart.findViewHolderForAdapterPosition(position)
                if (holder is CartProductAdapter.CartProductViewHolder) {
                    cartAdapter.decreaseQuantity(cartProduct, holder)
                    saveProductsToSharedPreferences(cartAdapter.differ.currentList)
                    updateTotalPrice(cartAdapter.differ.currentList)
                    val deletedRows = cartDatabaseHelper.deleteCartProduct(cartProduct.product.id)                }
            }
        }
        cartAdapter.setCartAdapterListener(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        cartDatabaseHelper.close()
    }
    override fun onItemRemoved(products: List<CartProduct>) {
        updateTotalPrice(products)
        saveProductsToSharedPreferences(products)
    }

    private fun hideEmptyCart() {
        binding.apply {
            layoutCartEmpty.visibility = View.GONE
        }
    }

    private fun showEmptyCart() {
        binding.apply {
            layoutCartEmpty.visibility = View.VISIBLE
        }    }

    private fun hideOtherView() {
        binding.apply {
            recyclerViewCart.visibility = View.GONE
            btnCheckOut.visibility = View.GONE
            totalBoxContainer.visibility = View.GONE
        }
    }

    private fun showOtherView() {
        binding.apply {
            recyclerViewCart.visibility = View.VISIBLE
            btnCheckOut.visibility = View.VISIBLE
            totalBoxContainer.visibility = View.VISIBLE
        }
    }

    private fun clearTotalPrice(){
        binding.totalPriceTv.text = "$0.00"
    }




    private fun setupCartRv() {
        binding.recyclerViewCart.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = cartAdapter


        }

    }

    override fun updateTotalPrice(products: List<CartProduct>) {
        val totalPrice = products.sumOf { it.product.price.toDouble() * it.quantity.toDouble() }.toFloat()
        binding.totalPriceTv.text = "$${String.format("%.2f", totalPrice)}"


    }





    override fun saveProductsToSharedPreferences(products: List<CartProduct>) {
        val sharedPreferences = requireContext().getSharedPreferences("CartPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val gson = Gson()
        val productsJson = gson.toJson(products)

        editor.putString(sharedPreferencesKey, productsJson)
        editor.apply()

    }

    private fun loadProductsFromSharedPreferences(): MutableList<CartProduct> {
        val sharedPreferences = requireContext().getSharedPreferences("CartPreferences", Context.MODE_PRIVATE)
        val productsJson = sharedPreferences.getString(sharedPreferencesKey, null)

        val gson = Gson()
        val productsType = object : TypeToken<List<CartProduct>>() {}.type

        val products = gson.fromJson<List<CartProduct>>(productsJson, productsType) ?: mutableListOf()

        updateTotalPrice(products)

        return products.toMutableList()
    }

    private fun calculateTotalPrice(cartProducts: List<CartProduct>, productPrices: Map<Int, Float>): Float {
        var totalPrice = 0f
        for (cartProduct in cartProducts) {
            val productId = cartProduct.product.id
            val productPrice = productPrices[productId] ?: 0f
            totalPrice += productPrice * cartProduct.quantity
        }
        return totalPrice
    }
    private fun loadProductPricesFromSharedPreferences(callback: (Map<Int, Float>) -> Unit) {
        val sharedPreferences = requireContext().getSharedPreferences("ProductPrices", Context.MODE_PRIVATE)
        val productPricesJson = sharedPreferences.getString("PRODUCT_PRICES", null)

        val gson = Gson()
        val productPricesType = object : TypeToken<Map<Int, Float>>() {}.type

        val productPrices = gson.fromJson<Map<Int, Float>>(productPricesJson, productPricesType) ?: emptyMap()

        callback(productPrices)
    }
    private fun clearCartDatFromSharedPreferences(){
        val sharedPreferences = requireContext().getSharedPreferences("CartPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.remove(sharedPreferencesKey)
        editor.apply()
    }



}