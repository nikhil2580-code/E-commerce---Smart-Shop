package com.nikhilkhairnar.shopsmart

import android.os.Parcelable
import java.io.Serializable


class Address(
    val addressTitle : String,
    val fullName : String,
    val street : String,
    val phone : String,
    val city : String,
    val state : String
): Serializable {

    constructor(): this("","",",","","","")
}