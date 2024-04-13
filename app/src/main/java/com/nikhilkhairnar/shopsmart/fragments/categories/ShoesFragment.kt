package com.nikhilkhairnar.shopsmart.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.nikhilkhairnar.shopsmart.Product
import com.nikhilkhairnar.shopsmart.R
import java.io.Serializable

class ShoesFragment:BaseCategoryFragment() {

    private val shoesProducts = listOf(
        Product(1, "Athletic Boost X", "Shoes", 79.99f, "Lightweight and comfortable athletic shoes for your daily workouts.", images = getProductImages(R.drawable.shoes_1), 15f),
        Product(2, "UltraFlex Running Shoes", "Shoes", 89.95f, "Flexible running shoes with advanced cushioning for a smooth run.", images = getProductImages(R.drawable.shoes_2), 10f),
        Product(3, "AirMax Pro 3", "Shoes", 99.99f, "Air-cushioned shoes for maximum comfort and support during your workouts.", images = getProductImages(R.drawable.shoes_3), 20f),
        Product(4, "TrailBlazer Hiking Boots", "Shoes", 129.79f, "Sturdy hiking boots with excellent traction for adventurous hikes.", images = getProductImages(R.drawable.shoes_4), 25f),
        Product(5, "Casual Comfort Loafers", "Shoes", 59.99f, "Casual loafers designed for everyday comfort and style.", images = getProductImages(R.drawable.shoes_5), 15f),
        Product(6, "Sprint Pro Track Shoes", "Shoes", 89.99f, "Professional track shoes for sprinters with optimal grip and speed.", images = getProductImages(R.drawable.shoes_6), 18f),
        Product(7, "All-Terrain Trail Shoes", "Shoes", 109.89f, "Versatile trail shoes suitable for various terrains and outdoor activities.", images = getProductImages(R.drawable.shoes_08), 22f),
        Product(8, "ComfyFit Walking Shoes", "Shoes", 69.95f, "Walking shoes designed for comfort and support during long walks.", images = getProductImages(R.drawable.shoes_08), 12f),
        Product(9, "YogaFlex Barefoot Shoes", "Shoes", 49.50f, "Flexible barefoot shoes for yoga and natural movement exercises.", images = getProductImages(R.drawable.shoes_09), 15f),
        Product(10, "UrbanStyle Sneakers", "Shoes", 79.99f, "Stylish sneakers for urban fashion enthusiasts, perfect for casual outings.", images = getProductImages(R.drawable.shoes_10), 10f),
        Product(11, "PowerLift Gym Shoes", "Shoes", 74.79f, "Weightlifting shoes with enhanced stability for powerful lifts in the gym.", images = getProductImages(R.drawable.shoes_1), 20f),
        Product(12, "RetroRunner Vintage Sneakers", "Shoes", 89.99f, "Vintage-style sneakers with a modern twist, ideal for retro fashion lovers.", images = getProductImages(R.drawable.shoes_2), 18f),
        Product(13, "CrossFit Pro Training Shoes", "Shoes", 99.99f, "CrossFit training shoes designed for intense workouts and varied movements.", images = getProductImages(R.drawable.shoes_09), 15f),
        Product(14, "CasualChic Slip-Ons", "Shoes", 64.99f, "Elegant slip-on shoes for casual and semi-formal occasions.", images = getProductImages(R.drawable.shoes_10), 12f),
        Product(15, "HighJump Athletic Shoes", "Shoes", 84.89f, "Athletic shoes designed for high jumpers, offering excellent ankle support.", images = getProductImages(R.drawable.shoes_08), 20f),
        Product(16, "WaterWalk Aqua Shoes", "Shoes", 54.99f, "Aqua shoes with non-slip soles for water sports and beach activities.", images = getProductImages(R.drawable.shoes_08), 15f),
        Product(17, "ProFormance Running Shoes", "Shoes", 94.89f, "Performance-focused running shoes for competitive runners seeking speed.", images = getProductImages(R.drawable.shoes_6), 18f),
        Product(18, "EcoFriendly Canvas Sneakers", "Shoes", 39.99f, "Canvas sneakers made from eco-friendly materials, perfect for conscious consumers.", images = getProductImages(R.drawable.shoes_5), 10f),
        Product(19, "TrekMaster Hiking Boots", "Shoes", 114.99f, "Durable hiking boots for trekking enthusiasts, offering superior protection and grip.", images = getProductImages(R.drawable.shoes_4), 22f),
        Product(20, "StreetStyle Skate Shoes", "Shoes", 74.89f, "Skate shoes with reinforced panels for skateboarders seeking durability.", images = getProductImages(R.drawable.shoes_3), 18f),
        Product(21, "CasualComfy Moccasins", "Shoes", 49.99f, "Comfortable moccasins designed for casual indoor wear and relaxation.", images = getProductImages(R.drawable.shoes_08), 10f),
        Product(22, "ActiveFlex Sport Shoes", "Shoes", 79.99f, "Sport shoes with flexible soles, ideal for various sports and physical activities.", images = getProductImages(R.drawable.shoes_1), 15f),




        )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpShoesProducts()
        val products = productViewModel.product

        bestProductAdapter.onClick = {
            val b = Bundle().apply { putSerializable("product",it as Serializable) }
            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment,b)
        }
    }
    private fun getProductImages(vararg resourceIds: Int): List<Int> {
        return resourceIds.toList()
    }

    private fun setUpShoesProducts() {
        bestProductAdapter.submitList(shoesProducts)
    }

}