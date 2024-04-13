package com.nikhilkhairnar.shopsmart


import android.os.Parcelable
import com.nikhilkhairnar.shopsmart.Product
import kotlinx.android.parcel.Parcelize
@Parcelize
data class CartProduct(
    val product: Product,
    val quantity: Int
):Parcelable{
    constructor() : this(Product(), 1)
}