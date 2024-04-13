package com.nikhilkhairnar.shopsmart.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.nikhilkhairnar.shopsmart.Product
import com.nikhilkhairnar.shopsmart.R
import java.io.Serializable

class ComputerFragment:BaseCategoryFragment() {

    private val computerProducts = listOf(
        Product(1, "SwiftTech Gaming PC", "Computer", 1299.99f, "Experience top-tier gaming performance with the SwiftTech Gaming PC.", images = getProductImages(R.drawable.computer_01), null),
        Product(2, "UltraBook Pro X", "Computer", 899.95f, "A sleek and powerful ultrabook designed for professionals and creative enthusiasts.", images = getProductImages(R.drawable.computer_02), null),
        Product(3, "CyberFusion Desktop", "Computer", 1599.99f, "Unleash the power of AI and high-speed processing with the CyberFusion Desktop.", images = getProductImages(R.drawable.computer_03), null),
        Product(4, "CreativeWorkstation 5000", "Computer", 2399.79f, "Designed for artists and content creators, this workstation delivers exceptional rendering performance.", images = getProductImages(R.drawable.computer_06), null),
        Product(5, "OfficePro All-in-One PC", "Computer", 699.99f, "Boost productivity with the OfficePro All-in-One PC, perfect for office and business use.", images = getProductImages(R.drawable.computer_05), null),
        Product(6, "Gamer's Choice Desktop", "Computer", 1899.99f, "Built for gaming enthusiasts, this desktop PC ensures seamless gameplay and stunning graphics.", images = getProductImages(R.drawable.computer_02), null),
        Product(7, "CreativePro Laptop X", "Computer", 1299.89f, "Fuel your creativity with the high-performance CreativePro Laptop X.", images = getProductImages(R.drawable.laptop_01), null),
        Product(8, "BusinessElite Mini PC", "Computer", 549.95f, "A compact and efficient mini PC tailored for business professionals.", images = getProductImages(R.drawable.computer_08), null),
        Product(9, "GamingBeast Ultimate", "Computer", 2199.50f, "Dominate the gaming arena with the GamingBeast Ultimate PC, engineered for hardcore gamers.", images = getProductImages(R.drawable.computer_09), null),
        Product(10, "PowerPro Workstation", "Computer", 1699.89f, "Handle intensive tasks with ease using the PowerPro Workstation, a powerhouse for professionals.", images = getProductImages(R.drawable.computer_10), null),
        Product(11, "HomeEntertainment Center", "Computer", 799.99f, "Transform your home into an entertainment hub with this multimedia-centric computer.", images = getProductImages(R.drawable.computer_08), null),
        Product(12, "UltraThin Laptop", "Computer", 1099.95f, "Stay productive on the go with the UltraThin Laptop, designed for portability and performance.", images = getProductImages(R.drawable.laptop_01), null),
        Product(13, "Developer's Dream PC", "Computer", 1899.79f, "A dream machine for developers, offering rapid coding and compilation capabilities.", images = getProductImages(R.drawable.computer_03), null),
        Product(14, "StudentEssentials Laptop", "Computer", 699.99f, "Ideal for students, this laptop balances performance and affordability.", images = getProductImages(R.drawable.computer_02), null),
        Product(15, "ProDesigner Workstation", "Computer", 2599.89f, "Elevate your design projects with the ProDesigner Workstation, optimized for graphic designers.", images = getProductImages(R.drawable.computer_08), null),
        Product(16, "BusinessClass Desktop", "Computer", 1399.95f, "A reliable and efficient desktop solution for corporate environments.", images = getProductImages(R.drawable.computer_06), null),
        Product(17, "Streamline All-in-One", "Computer", 999.89f, "Simplify your workspace with the Streamline All-in-One PC, featuring a clutter-free design.", images = getProductImages(R.drawable.computer_10), null),
        Product(18, "Multimedia Maestro Laptop", "Computer", 1199.99f, "Immerse yourself in multimedia experiences with the Multimedia Maestro Laptop.", images = getProductImages(R.drawable.computer_01), null),
        Product(19, "GamingSpectre Desktop", "Computer", 1799.50f, "Experience gaming like never before with the GamingSpectre Desktop, tailored for gamers.", images = getProductImages(R.drawable.computer_05), null),
        Product(20, "HomeOffice Essential PC", "Computer", 899.89f, "Enhance your home office setup with the HomeOffice Essential PC, designed for productivity.", images = getProductImages(R.drawable.computer_09), null),
        Product(21, "BudgetFriendly Laptop", "Computer", 649.99f, "An affordable yet reliable laptop solution for everyday computing needs.", images = getProductImages(R.drawable.laptop_04), null),
        Product(22, "HighPerformance Workstation", "Computer", 2799.99f, "Achieve unparalleled performance with the HighPerformance Workstation, built for professionals.", images = getProductImages(R.drawable.computer_03), null)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpComputerProducts()

        bestProductAdapter.onClick = {
            val b = Bundle().apply { putSerializable("product",it as Serializable) }
            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment,b)
        }

    }
    private fun getProductImages(vararg resourceIds: Int): List<Int> {
        return resourceIds.toList()
    }

    private fun setUpComputerProducts() {
        bestProductAdapter.submitList(computerProducts)    }
}
