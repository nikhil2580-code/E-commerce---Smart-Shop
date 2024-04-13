package com.nikhilkhairnar.shopsmart.viemodel

import androidx.lifecycle.ViewModel
import com.nikhilkhairnar.shopsmart.Product

class ProductViewModel : ViewModel() {

    var product = mutableListOf<Product>()




    fun submitList(products: List<Product>) {
        this.product = product
    }
}