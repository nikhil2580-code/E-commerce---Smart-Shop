package com.nikhilkhairnar.shopsmart

object PriceCalculator {

    fun calculateTotalPrice(cartProduct: CartProduct): Float {
        val basePrice = cartProduct.product.price
        val discount = cartProduct.product.price // You might want to adjust this logic based on your actual discount calculation
        val quantity = cartProduct.quantity.coerceIn(1, 10)
        val discountPrice = basePrice * (1 - discount / 100)
        return discountPrice * quantity
    }
}