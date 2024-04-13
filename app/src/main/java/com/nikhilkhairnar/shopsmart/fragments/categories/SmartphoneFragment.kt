package com.nikhilkhairnar.shopsmart.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.nikhilkhairnar.shopsmart.Product
import com.nikhilkhairnar.shopsmart.R
import java.io.Serializable

class SmartphoneFragment: BaseCategoryFragment(){


    private val smartPhoneProducts = listOf(

        Product(1, "Galaxy S22 Ultra", "Smartphone", 1299.99f, "Experience the ultimate in smartphone technology with the Galaxy S22 Ultra.", images = getProductImages(R.drawable.smart_01), null),
        Product(2, "Google Nexus 5", "Smartphone", 1399.95f, "The latest iPhone 14 Pro Max offers stunning performance and advanced camera capabilities.", images = getProductImages(R.drawable.smart_02), null),
        Product(3, "OnePlus 10 Pro", "Smartphone", 899.99f, "A flagship killer, the OnePlus 10 Pro delivers top-notch features at an affordable price.", images = getProductImages(R.drawable.smart_03), null),
        Product(4, "Pixel 7 XL", "Smartphone", 999.79f, "Capture perfect moments with the Pixel 7 XL's exceptional camera and enjoy seamless performance.", images = getProductImages(R.drawable.smart_04), null),
        Product(5, "Xperia Pro III", "Smartphone", 1199.99f, "Sony's Xperia Pro III combines cutting-edge technology with a sleek design for professionals.", images = getProductImages(R.drawable.smart_05), null),
        Product(6, "Mi 12 Ultra", "Smartphone", 1099.99f, "Xiaomi's Mi 12 Ultra offers unparalleled speed and an impressive camera setup for photography enthusiasts.", images = getProductImages(R.drawable.smart_06), null),
        Product(7, "ZenFone 9 Deluxe", "Smartphone", 849.89f, "Asus ZenFone 9 Deluxe boasts a deluxe design and high-end performance, perfect for tech enthusiasts.", images = getProductImages(R.drawable.smart_01), null),
        Product(8, "Galaxy Note 22", "Smartphone", 1299.95f, "Samsung's Galaxy Note 22 comes with an S Pen and powerful features tailored for productivity.", images = getProductImages(R.drawable.smart_08), null),
        Product(9, "Redmi K50 Pro", "Smartphone", 699.50f, "Redmi K50 Pro offers impressive specs and an affordable price, making it a popular choice.", images = getProductImages(R.drawable.smart_09), null),
        Product(10, "ROG Phone 6 Ultimate", "Smartphone", 1499.99f, "Designed for gamers, the ROG Phone 6 Ultimate provides a gaming experience like no other.", images = getProductImages(R.drawable.smart_10), null),
        Product(11, "LG V80 ThinQ", "Smartphone", 899.79f, "LG V80 ThinQ combines sleek design with powerful features for a premium smartphone experience.", images = getProductImages(R.drawable.smart_01), null),
        Product(12, "Motorola Edge X30", "Smartphone", 799.99f, "Motorola Edge X30 offers a high-refresh-rate display and impressive camera capabilities.", images = getProductImages(R.drawable.smart_02), null),
        Product(13, "Honor 50 Pro", "Smartphone", 799.99f, "Honor 50 Pro boasts a stylish design and advanced camera features, perfect for photography enthusiasts.", images = getProductImages(R.drawable.smart_03), null),
        Product(14, "Realme GT Neo 3", "Smartphone", 499.99f, "Realme GT Neo 3 delivers impressive performance and 5G capabilities at an affordable price point.", images = getProductImages(R.drawable.smart_04), null),
        Product(15, "Vivo X80 Pro+", "Smartphone", 1099.89f, "Vivo X80 Pro+ offers a powerful processor and a stunning display for an immersive smartphone experience.", images = getProductImages(R.drawable.smart_05), null),
        Product(16, "Oppo Find X5 Pro", "Smartphone", 1199.95f, "Oppo Find X5 Pro features an impressive camera system and a sleek design for a premium feel.", images = getProductImages(R.drawable.smart_06), null),
        Product(17, "Nokia 10 PureView", "Smartphone", 899.89f, "Nokia 10 PureView focuses on photography, providing a high-resolution multi-lens camera setup.", images = getProductImages(R.drawable.smart_08), null),
        Product(18, "Black Shark 5 Pro", "Smartphone", 999.50f, "Black Shark 5 Pro is designed for gaming enthusiasts, delivering top-notch gaming performance.", images = getProductImages(R.drawable.smart_09), null),
        Product(19, "ZTE Axon 40 Pro", "Smartphone", 799.99f, "ZTE Axon 40 Pro offers a unique under-display camera and impressive display technology.", images = getProductImages(R.drawable.smart_10), null),
        Product(20, "Alcatel 5X Plus", "Smartphone", 299.89f, "Alcatel 5X Plus provides essential features at an affordable price, catering to budget-conscious users.", images = getProductImages(R.drawable.smart_06), null),
        Product(21, "Google Pixel 6a", "Smartphone", 599.99f, "Google Pixel 6a offers a pure Android experience and a fantastic camera for photography lovers.", images = getProductImages(R.drawable.smart_03), null),
        Product(22, "Xiaomi Poco X5 Pro", "Smartphone", 399.99f, "Xiaomi Poco X5 Pro delivers impressive performance and a large battery at an affordable price.", images = getProductImages(R.drawable.smart_05), null),


        )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSmartphoneProducts()
        val  products =  productViewModel.product
        bestProductAdapter.onClick = {
            val b = Bundle().apply { putSerializable("product",it as Serializable) }
            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment,b)
        }

    }
    private fun getProductImages(vararg resourceIds: Int): List<Int> {
        return resourceIds.toList()
    }

    private fun setUpSmartphoneProducts() {
        bestProductAdapter.submitList(smartPhoneProducts)    }
}


