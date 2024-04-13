package com.nikhilkhairnar.shopsmart.order

import android.os.Parcelable
import com.nikhilkhairnar.shopsmart.Address
import com.nikhilkhairnar.shopsmart.CartProduct
import kotlinx.android.parcel.Parcelize
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
@Parcelize
data class Order(
    val orderStatus: String = "",
    val totalPrice:Float = 0f,
    val products: List<CartProduct> = emptyList(),
    val address: Address = Address(),
    val date: String = SimpleDateFormat("yyyy-MM_dd", Locale.ENGLISH).format(Date()),
    val orderId: String = ""

):Parcelable


