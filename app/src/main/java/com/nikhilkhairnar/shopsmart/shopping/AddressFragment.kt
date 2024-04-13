package com.nikhilkhairnar.shopsmart.shopping

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nikhilkhairnar.shopsmart.Address
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.SharedPreferencesHelper
import com.nikhilkhairnar.shopsmart.databinding.FragmentAddressBinding
import com.nikhilkhairnar.shopsmart.databinding.FragmentCartBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddressFragment: Fragment() {
    private lateinit var binding: FragmentAddressBinding
    private lateinit var sharedPreferencesHelper: SharedPreferencesHelper


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressBinding.inflate(inflater)
        sharedPreferencesHelper = SharedPreferencesHelper(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            val progressBar = progressbarAddress

            buttonSave.setOnClickListener {
                Log.d("AddressFragment", "Button Clicked");
                val addressTitle = edAddressTitle.text.toString()
                    val fullName = edFullName.text.toString()
                    val street = edStreet.text.toString()
                    val phone = edPhone.text.toString()
                    val city = edCity.text.toString()
                    val state = edState.text.toString()
                    val address = Address(addressTitle, fullName, street, phone, city, state)

                    progressBar.visibility = View.VISIBLE

                    CoroutineScope(Dispatchers.IO).launch {
                        sharedPreferencesHelper.saveAddress(address)

                        withContext(Dispatchers.Main){
                            progressBar.visibility = View.GONE

                            findNavController().navigate(R.id.action_addressFragment_to_billingFragment)

                        }

                    }



            }

        }
    }
}