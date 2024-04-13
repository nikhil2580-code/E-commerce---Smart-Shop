package com.nikhilkhairnar.shopsmart.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhilkhairnar.shopsmart.Product
import com.nikhilkhairnar.shopsmart.ProductDataSource
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.adapters.BestDealAdapter
import com.nikhilkhairnar.shopsmart.adapters.BestProductAdapter
import com.nikhilkhairnar.shopsmart.adapters.SpecialProductAdapter
import com.nikhilkhairnar.shopsmart.databinding.FragmentMainCategoryBinding
import java.io.Serializable

class MainCategoryFragment : Fragment(R.layout.fragment_main_category) {

    private lateinit var binding: FragmentMainCategoryBinding
    private lateinit var specialProductAdapter: SpecialProductAdapter
    private lateinit var bestDealAdapter: BestDealAdapter
    private lateinit var bestProductAdapter: BestProductAdapter
    // Declare product lists for each adapter
    private val specialProducts = listOf(
        Product(31, "UltraVision HD", "Main", 449.99f, "See the world in high definition with this HD camera offering crystal-clear imaging.", images = getProductImages(R.drawable.camera_108), 5f),        Product(19, "GamingSpectre Desktop", "Computer", 1799.50f, "Experience gaming like never before with the GamingSpectre Desktop, tailored for gamers.", images = getProductImages(R.drawable.computer_05), null),
        Product(32, "HomeOffice Essential PC", "Computer", 899.89f, "Enhance your home office setup with the HomeOffice Essential PC, designed for productivity.", images = getProductImages(R.drawable.computer_09), null),
        Product(33, "BudgetFriendly Laptop", "Computer", 649.99f, "An affordable yet reliable laptop solution for everyday computing needs.", images = getProductImages(R.drawable.laptop_04), null),
        Product(34, "HighPerformance Workstation", "Computer", 2799.99f, "Achieve unparalleled performance with the HighPerformance Workstation, built for professionals.", images = getProductImages(R.drawable.computer_03), null),
        Product(35, "Elegant Dining Chair", "Main", 149.99f, "A comfortable and stylish dining chair for your dining room.", images = getProductImages(R.drawable.furniture_07), null),

    )

    private val bestDealProducts = listOf(
        Product(36, "Athletic Boost X", "Shoes", 79.99f, "Lightweight and comfortable athletic shoes for your daily workouts.", images = getProductImages(R.drawable.shoes_1), 1f),
        Product(37, "ZTE Axon 40 Pro", "Smartphone", 799.99f, "ZTE Axon 40 Pro offers a unique under-display camera and impressive display technology.", images = getProductImages(R.drawable.smart_10), null),
        Product(38, "Alcatel 5X Plus", "Smartphone", 299.89f, "Alcatel 5X Plus provides essential features at an affordable price, catering to budget-conscious users.", images = getProductImages(R.drawable.smart_06), null),
        Product(39, "Google Pixel 6a", "Smartphone", 599.99f, "Google Pixel 6a offers a pure Android experience and a fantastic camera for photography lovers.", images = getProductImages(R.drawable.smart_03), null),
        Product(40, "Xiaomi Poco X5 Pro", "Smartphone", 399.99f, "Xiaomi Poco X5 Pro delivers impressive performance and a large battery at an affordable price.", images = getProductImages(R.drawable.smart_05), null),

    )

    private val bestProducts = listOf(
        Product(41, "BudgetFriendly Laptop", "Computer", 649.99f, "An affordable yet reliable laptop solution for everyday computing needs.", images = getProductImages(R.drawable.laptop_04), null),
        Product(42, "Galaxy Note 22", "Smartphone", 1299.95f, "Samsung's Galaxy Note 22 comes with an S Pen and powerful features tailored for productivity.", images = getProductImages(R.drawable.smart_08), null),
        Product(43, "Metal Frame Dining Chair", "Main", 89.99f, "A modern dining chair with a sturdy metal frame and comfortable padding.", images = getProductImages(R.drawable.furniture_06), null),
        Product(44, "Elegant Dining Chair", "Main", 149.99f, "A comfortable and stylish dining chair for your dining room.", images = getProductImages(R.drawable.furniture_07), null),
        Product(45, "ZoomPro Plus", "Main",  899.50f, " Zoom in on distant subjects without losing image quality with this powerful zoom camera.", images = getProductImages(R.drawable.camera_107),null),
        Product(46, "UltraVision HD", "Main", 449.99f, " See the world in high definition with this HD camera offering crystal-clear imaging.", images = getProductImages(R.drawable.camera_108),null),
        Product(47, "Camera Model 1", "Main", 599.99f, "Capture stunning moments with high-resolution images and advanced autofocus technology.",images = getProductImages(R.drawable.camrea_101), null),
        Product(48, "Digital Vision Pro", "Main", 899.49f, "Experience professional-grade photography with this feature-packed digital camera.", images = getProductImages(R.drawable.camera_106), null),
        Product(49, "EagleEye 4K Ultra", "Main", 1299.99f, "Shoot breathtaking 4K videos and crisp photos with this cutting-edge camera.", images = getProductImages(R.drawable.camera_104), null),
        Product(50, "All-Terrain Trail Shoes", "Shoes", 109.89f, "Versatile trail shoes suitable for various terrains and outdoor activities.", images = getProductImages(R.drawable.shoes_08), 22f),
        Product(51, "ComfyFit Walking Shoes", "Shoes", 69.95f, "Walking shoes designed for comfort and support during long walks.", images = getProductImages(R.drawable.shoes_1), 12f),
        Product(52, "BusinessElite Mini PC", "Computer", 549.95f, "A compact and efficient mini PC tailored for business professionals.", images = getProductImages(R.drawable.computer_08), null),
        Product(53, "GamingBeast Ultimate", "Computer", 2199.50f, "Dominate the gaming arena with the GamingBeast Ultimate PC, engineered for hardcore gamers.", images = getProductImages(R.drawable.computer_09), null),
        Product(54, "PowerPro Workstation", "Computer", 1699.89f, "Handle intensive tasks with ease using the PowerPro Workstation, a powerhouse for professionals.", images = getProductImages(R.drawable.computer_10), null),


    )



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        specialProductAdapter = SpecialProductAdapter(findNavController())
        bestDealAdapter = BestDealAdapter(findNavController())
        bestProductAdapter = BestProductAdapter(findNavController())

        specialProductAdapter.onClick = {
            val b = Bundle().apply { putSerializable("product", it as Serializable) }
            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment, b)
        }
        bestDealAdapter.onClick = {
            val product = bestDealProducts[0]
            val b = Bundle().apply { putSerializable("product", it as Serializable) }
            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment, b)
        }
        bestProductAdapter.onClick = {
            val b = Bundle().apply { putSerializable("product",it as Serializable) }
            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment,b)
        }
        // Set up RecyclerViews with adapters and product lists
        binding.rvBestProducts.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = bestProductAdapter
        }
        bestProductAdapter.submitList(bestProducts)

        binding.rvBestDealProducts.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = bestDealAdapter
        }
        bestDealAdapter.submitList(bestDealProducts)

        binding.specialProduct.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = specialProductAdapter
        }
        specialProductAdapter.submitList(specialProducts)

    }


    private fun getProductImages(vararg resourceIds: Int): List<Int> {
        return resourceIds.toList()
    }



}
