package com.nikhilkhairnar.shopsmart

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesHelper(context: Context){
    private val sharedPreferences : SharedPreferences =
        context.getSharedPreferences("AddressPreferences", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()


    fun saveAddress(address: Address) {
        val editor = sharedPreferences.edit()
        editor.putString("addressTitle", address.addressTitle)
        editor.putString("fullName", address.fullName)
        editor.putString("street", address.street)
        editor.putString("phone", address.phone)
        editor.putString("city", address.city)
        editor.putString("state", address.state)
        editor.apply()
    }

    fun getAddress(): Address {
        return Address(
            sharedPreferences.getString("addressTitle", "")?: "",
            sharedPreferences.getString("fullName", "")?: "",
            sharedPreferences.getString("street", "")?: "",
            sharedPreferences.getString("phone", "")?: "",
            sharedPreferences.getString("city", "")?: "",
            sharedPreferences.getString("state", "")?: "",
        )

    }
}