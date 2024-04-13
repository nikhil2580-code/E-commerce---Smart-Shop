package com.nikhilkhairnar.shopsmart.shopping

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.nikhilkhairnar.shopsmart.activities.LoginRegisterActivity
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var sharedPreferences: SharedPreferences




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("Login", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "")

        sharedPreferences = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("userName", "")

        binding.tvUserName.text = "$userName"

        binding.linearAllOrders.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_allOrdersFragment2)
        }

        binding.linearBilling.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_billingFragment2)
        }
        binding.linearLogOut.setOnClickListener {
            // Clear user login state
            with(sharedPreferences.edit()) {
                putBoolean("isLoggedIn", false)
                apply()
            }

            // Navigate to the introduction fragment after logout
            val intent = Intent(requireContext(), LoginRegisterActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().onBackPressedDispatcher.addCallback(this){
            false
        }

    }

}