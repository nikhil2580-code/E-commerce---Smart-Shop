package com.nikhilkhairnar.shopsmart.lognRegister

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.databinding.FragmentSignupBinding
import java.util.prefs.Preferences


class SignUpFragment: Fragment() {

  private lateinit var binding: FragmentSignupBinding
    private lateinit var navController: NavController
    private lateinit var  sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = NavHostFragment.findNavController(this)


        binding.btnSignUp.setOnClickListener {
            performSingUp()
        }
        binding.alreadyHaveAnAccount.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment2_to_loginFragment3)

        }
        sharedPreferences = requireActivity().getSharedPreferences("Signup", Context.MODE_PRIVATE)
    }

    private fun performSingUp() {
        val name = binding.usernameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val confirmPassword = binding.confirmPasswordEditText.text.toString()


        with(sharedPreferences.edit()){
            putString("name", name)
            putString("email", email)
            putString("password", password)
            apply()

        }

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
            if(name.isEmpty()){
                binding.usernameEditText.error = "Name is required"
            }
            if(email.isEmpty()){
                binding.emailEditText.error = "Email is required"
            }
            if(password.isEmpty()) {
                binding.passwordEditText.error = "Password is required"
            }
            if(confirmPassword.isEmpty()){
                binding.confirmPasswordEditText.error= "Confirm password is required"
            }
        }else if (password != confirmPassword) {
            binding.confirmPasswordEditText.error = "Password do not match"
        } else if (password.length < 6 || confirmPassword.length < 6) {
                binding.passwordEditText.error = "Password must be at least 6 characters"
            }  else {
            navController.navigate(R.id.action_signUpFragment2_to_loginFragment3)
            // Perform sign-up logic here
            // If sign-up is successful, navigate to the LoginFragment
            // Otherwise, show an error message
        }
    }
}