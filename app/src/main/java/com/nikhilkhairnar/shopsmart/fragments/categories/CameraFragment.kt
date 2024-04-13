package com.nikhilkhairnar.shopsmart.fragments.categories

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.nikhilkhairnar.shopsmart.Product
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.adapters.BestProductAdapter
import java.io.Serializable

class CameraFragment : BaseCategoryFragment() {

    private val cameraProducts = listOf(
        Product(22, "Camera Model 1", "Main", 599.99f, " Capture stunning moments with high-resolution images and advanced autofocus technology.", images = getProductImages(R.drawable.camera_102), null),
        Product(17, "Digital Vision Pro", "Main", 899.49f, "Description: Experience professional-grade photography with this feature-packed digital camera.", images = getProductImages(R.drawable.camera_103), null),
        Product(11, "EagleEye 4K Ultra", "Main", 1299.99f, "Shoot breathtaking 4K videos and crisp photos with this cutting-edge camera.", images = getProductImages(R.drawable.camera_104), null),
        Product(11, "LensMaster 5000X", "Main", 699.79f, "Perfect for photography enthusiasts, this camera offers superior lens options and manual controls.", images = getProductImages(R.drawable.camrea_101),null),
        Product(11, "Pixel Perfect X", "Main", 749.99f, " Achieve pixel-perfect clarity with this advanced camera equipped with image stabilization technology", images = getProductImages(R.drawable.img_444),null),
        Product(11, "SnapShot Pro Elite", "Main", 499.95f, "Your ideal companion for capturing fast-action shots and low-light scenes.", images = getProductImages(R.drawable.camera_105),null),
        Product(11, "CaptureX 360mera", "Main",  1099.89f, " Experience the world of panoramic photography with this 360-degree camera.", images = getProductImages(R.drawable.camera_106),null),
        Product(11, "FocusMaster DSLR", "Main", 799.99f, "Unleash your creativity with this DSLR camera featuring multiple focus modes and artistic filters.", images = getProductImages(R.drawable.camera_102),null),
        Product(11, "ZoomPro Plus", "Main",  899.50f, " Zoom in on distant subjects without losing image quality with this powerful zoom camera.", images = getProductImages(R.drawable.camera_107),null),
        Product(11, "UltraVision HD", "Main", 449.99f, " See the world in high definition with this HD camera offering crystal-clear imaging.", images = getProductImages(R.drawable.camera_108),null),
        Product(1, "Camera Model 1", "Main", 599.99f, "Capture stunning moments with high-resolution images and advanced autofocus technology.",images = getProductImages(R.drawable.img_444), null),
        Product(2, "Digital Vision Pro", "Main", 899.49f, "Experience professional-grade photography with this feature-packed digital camera.", images = getProductImages(R.drawable.camera_106), null),
        Product(3, "EagleEye 4K Ultra", "Main", 1299.99f, "Shoot breathtaking 4K videos and crisp photos with this cutting-edge camera.", images = getProductImages(R.drawable.camera_104), null),
        Product(4, "LensMaster 5000X", "Main", 699.79f, "Perfect for photography enthusiasts, this camera offers superior lens options and manual controls.", images = getProductImages(R.drawable.camera_108), null),
        Product(5, "Pixel Perfect X", "Main", 749.99f, "Achieve pixel-perfect clarity with this advanced camera equipped with image stabilization technology.", images = getProductImages(R.drawable.camera_102), null),
        Product(6, "SnapShot Pro Elite", "Main", 499.95f, "Your ideal companion for capturing fast-action shots and low-light scenes.", images = getProductImages(R.drawable.camera_105), null),
        Product(7, "CaptureX 360", "Main", 1099.89f, "Experience the world of panoramic photography with this 360-degree camera.", images =getProductImages(R.drawable.camera_109), null),
        Product(8, "FocusMaster DSLR", "Main", 799.99f, "Unleash your creativity with this DSLR camera featuring multiple focus modes and artistic filters.", images = getProductImages(R.drawable.product_img), null),
        Product(9, "ZoomPro Plus", "Main", 899.50f, "Zoom in on distant subjects without losing image quality with this powerful zoom camera.", images = getProductImages(R.drawable.camera_106), null),
        Product(10, "UltraVision HD", "Main", 449.99f, "See the world in high definition with this HD camera offering crystal-clear imaging.", images = getProductImages(R.drawable.camera_108), null),
        Product(11, "LensCraft X4", "Main", 679.29f, "Craft visually appealing compositions with this camera's versatile lens options.", images = getProductImages(R.drawable.camera_107), null),
        Product(12, "SpeedShot 2000", "Main", 999.00f, "Capture rapid sequences effortlessly with this high-speed shooting camera.", images = getProductImages(R.drawable.camrea_101), null),




        )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setUpCameraProducts()
   val  products =  productViewModel.product

        bestProductAdapter.onClick = {
            val b = Bundle().apply { putSerializable("product",it as Serializable) }
            findNavController().navigate(R.id.action_homeFragment_to_productDetailsFragment,b)
        }


    }

    private fun getProductImages(vararg resourceIds: Int): List<Int> {
        return resourceIds.toList()
    }

    private fun setUpCameraProducts() {
        bestProductAdapter.submitList(cameraProducts)    }




}
