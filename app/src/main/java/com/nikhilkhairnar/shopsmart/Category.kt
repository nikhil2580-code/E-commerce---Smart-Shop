package com.nikhilkhairnar.shopsmart

sealed class Category(val category: String) {

    object Camera : Category("Camera")
    object Cars : Category("Cars")
    object Computer : Category("Computer")
    object Smartphone : Category("SmartPhone")
    object Sport : Category("Sport")
}