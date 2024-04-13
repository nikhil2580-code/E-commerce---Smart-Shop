package com.nikhilkhairnar.shopsmart.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.databinding.ActivityShoppingBinding

class ShoppingActivity : AppCompatActivity() {

    private val binding by lazy { ActivityShoppingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navController = findNavController(R.id.shopping_host_fragment)
        binding.navView.setupWithNavController(navController)


    }

}
