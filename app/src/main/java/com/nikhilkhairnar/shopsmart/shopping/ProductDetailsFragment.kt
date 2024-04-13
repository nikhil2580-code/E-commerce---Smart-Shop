package com.nikhilkhairnar.shopsmart.shopping

import com.nikhilkhairnar.shopsmart.CartProduct
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.activities.ShoppingActivity
import com.nikhilkhairnar.shopsmart.databinding.FragmentProductDetailesBinding


class ProductDetailsFragment : Fragment() {

    private val args by navArgs<ProductDetailsFragmentArgs>()
    private lateinit var binding: FragmentProductDetailesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bottomNavigationView =
            (activity as ShoppingActivity).findViewById<BottomNavigationView>(
                R.id.nav_view
            )
        bottomNavigationView.visibility = View.VISIBLE
        binding = FragmentProductDetailesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val product = args.product


        if (product.images.isNotEmpty()) {
            binding.productImage.setImageResource(product.images[0])
        } else {
            // If there are no product images, you can set a default placeholder image
            binding.productImage.setImageResource(R.drawable.product_img)
        }

        binding.addToCartButton.setOnClickListener {

            val product  = args.product
            val cartProduct = CartProduct(product,1)
            val result = Bundle().apply {
                putSerializable("addedProduct", product)
            }
            setFragmentResult("productAdded", result)

            findNavController().navigate(R.id.action_productDetailsFragment_to_cartFragment)
            Snackbar.make(view,"Product successfully added to the cart",Snackbar.LENGTH_SHORT).show()


        }

        binding.apply {
            productNameTextView.text = product.name
            productNameTextPrice.text = "$ ${product.price}"
            productDescTextView.text = product.description
            if (product.images.isNotEmpty()) {
                Glide.with(productImage)
                    .load(product.images[0])
                    .into(productImage)
            } else {
                productImage.setImageResource(R.drawable.product_img)
            }
        }
    }
}
