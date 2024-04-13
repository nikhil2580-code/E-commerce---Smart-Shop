package com.nikhilkhairnar.shopsmart

object ProductDataSource {
    private var productList = listOf<Product>()

    fun initProductList(products: List<Product>) {
        productList = products
    }

    fun getProductList(): List<Product> {
        return productList
    }

    fun getProductById(productId: Int): Product? {
        return productList.find { it.id == productId }
    }
}
