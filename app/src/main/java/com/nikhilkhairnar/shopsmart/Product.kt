package com.nikhilkhairnar.shopsmart

import java.io.Serializable


data class Product(
    val id: Int,
    val name: String,
    val category: String,
    val price: Float,
    val description: String,
    val images: List<Int>,
    val offerPercentage: Float? = null


):Serializable{
    constructor():this(0, "", "", 0f, "", images = listOf(0))


}
