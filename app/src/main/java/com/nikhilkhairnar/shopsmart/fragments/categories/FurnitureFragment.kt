package com.nikhilkhairnar.shopsmart.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.nikhilkhairnar.shopsmart.Product
import com.nikhilkhairnar.shopsmart.R
import java.io.Serializable

class FurnitureFragment:BaseCategoryFragment() {

    private val furnitureProducts = listOf(
        Product(1, "Elegant Dining Chair", "Main", 149.99f, "A comfortable and stylish dining chair for your dining room.", images = getProductImages(R.drawable.furniture_07), null),
        Product(2, "Luxury Leather Sofa", "Main", 599.99f, "Relax in style with this luxurious leather sofa for your living room.", images = getProductImages(R.drawable.furniture_04), null),
        Product(3, "Modern Glass Dining Table", "Main", 349.95f, "A sleek glass dining table with a contemporary design for your dining area.", images = getProductImages(R.drawable.furniture_01), null),
        Product(4, "Wooden Coffee Table", "Main", 179.99f, "A sturdy wooden coffee table to complement your living room decor.", images = getProductImages(R.drawable.furniture_05), null),
        Product(5, "Classic Armchair", "Main", 199.50f, "An elegant armchair with a classic design, perfect for any room.", images = getProductImages(R.drawable.furniture_06), null),
        Product(6, "L-Shaped Sectional Sofa", "Main", 799.99f, "Create a cozy seating area with this spacious L-shaped sectional sofa.", images = getProductImages(R.drawable.furniture_04), null),
        Product(7, "Round Dining Table Set", "Main", 449.89f, "A complete dining table set with a round table and comfortable chairs.", images = getProductImages(R.drawable.furniture_03), null),
        Product(8, "Glass Top Center Table", "Main", 249.99f, "A modern center table with a glass top for a contemporary living room.", images = getProductImages(R.drawable.furniture_05), null),
        Product(9, "Wooden Dining Chair", "Main", 129.95f, "A classic wooden dining chair with a comfortable seat for your dining space.", images = getProductImages(R.drawable.furniture_10), null),
        Product(10, "Leather Recliner Sofa", "Main", 899.00f, "Experience ultimate relaxation with this leather recliner sofa.", images = getProductImages(R.drawable.furniture_04), null),
        Product(11, "Extendable Dining Table", "Main", 549.99f, "An extendable dining table to accommodate more guests during gatherings.", images = getProductImages(R.drawable.furniture_08), null),
        Product(12, "Marble Top Coffee Table", "Main", 379.79f, "A luxurious coffee table with a marble top for a touch of elegance.", images = getProductImages(R.drawable.furniture_05), null),
        Product(13, "Metal Frame Dining Chair", "Main", 89.99f, "A modern dining chair with a sturdy metal frame and comfortable padding.", images = getProductImages(R.drawable.furniture_06), null),
        Product(14, "Convertible Sofa Bed", "Main", 699.95f, "A space-saving sofa that can be transformed into a comfortable bed.", images = getProductImages(R.drawable.furniture_01), null),
        Product(15, "Rustic Wooden Dining Table", "Main", 479.99f, "A rustic-style dining table crafted from solid wood for a charming ambiance.", images = getProductImages(R.drawable.furniture_02), null),
        Product(16, "Glass Top Side Table", "Main", 159.95f, "A sleek side table with a glass top, ideal for placing beside your sofa.", images = getProductImages(R.drawable.furniture_05), null),
        Product(17, "Velvet Upholstered Chair", "Main", 129.89f, "A plush velvet upholstered chair with a modern design for your living space.", images = getProductImages(R.drawable.furniture_07), null),
        Product(18, "Sectional Corner Sofa", "Main", 899.50f, "Maximize your seating area with this sectional corner sofa.", images = getProductImages(R.drawable.furniture_04), null),
        Product(19, "Rectangular Dining Table", "Main", 349.99f, "A sturdy rectangular dining table with a timeless design for your home.", images = getProductImages(R.drawable.furniture_03), null),
        Product(20, "Teak Wood Tea Table", "Main", 259.89f, "A teak wood tea table with intricate craftsmanship for your living room.", images = getProductImages(R.drawable.furniture_05), null),
        Product(21, "Mid-Century Armchair", "Main", 179.95f, "A mid-century inspired armchair with wooden legs for a retro vibe.", images = getProductImages(R.drawable.furniture_06), null),
        Product(22, "Chaise Lounge Sofa", "Main", 749.99f, "Relax in style with this comfortable chaise lounge sofa.", images = getProductImages(R.drawable.furniture_04), null),

        )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpFurnitureProducts()
        val  products =  productViewModel.product

        bestProductAdapter.onClick = {
            val b = Bundle().apply { putSerializable("product",it as Serializable) }
            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment,b)
        }

    }
    private fun getProductImages(vararg resourceIds: Int): List<Int> {
        return resourceIds.toList()
    }


    private fun setUpFurnitureProducts() {
        bestProductAdapter.submitList(furnitureProducts)    }
}
