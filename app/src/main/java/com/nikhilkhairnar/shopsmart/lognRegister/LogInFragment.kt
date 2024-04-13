package com.nikhilkhairnar.shopsmart.lognRegister

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.nikhilkhairnar.shopsmart.R
import com.nikhilkhairnar.shopsmart.activities.ShoppingActivity
import com.nikhilkhairnar.shopsmart.databinding.FragmentLoginBinding
import com.nikhilkhairnar.shopsmart.dialog.setupBottomSheetDialog

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = NavHostFragment.findNavController(this)

        // Set click listeners for buttons and handle login logic here
        binding.btnLogIn.setOnClickListener {
            performLogin()
        }

        binding.textViewForgotPassword.setOnClickListener {
            setupBottomSheetDialog { email ->
                if (sendResetEmail(email)) {
                    showSnackbar("Reset link sent to your email")
                } else {
                    showSnackbar("Invalid email address")
                }
            }


        }


        binding.signUpTextView.setOnClickListener {
            navController.navigate(R.id.action_loginFragment3_to_signUpFragment2)
        }
        sharedPreferences = requireActivity().getSharedPreferences("Login", Context.MODE_PRIVATE)


    }

    private fun showSnackbar(message: String) {
        val snackbar = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        snackbar.show()

    }

    private fun sendResetEmail(email: String): Boolean {
        val savedEmail = sharedPreferences.getString("email", "")

        if (savedEmail == email) {
            // Implement logic to send reset email using your email sending service
            // For demonstration purposes, we'll just return true
            return false
        }
        return false
    }


    // Inside performLogin() function, after successful authentication
    private fun performLogin() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()

        // Assuming authentication is successful
        val isLoginSuccessful = isValidCredentials(email, password)

        if (isLoginSuccessful) {
            // Store user login state and user name
            with(sharedPreferences.edit()) {
                putBoolean("isLoggedIn", true)
                putString("email", email)
                putString("userName", "John Doe")
                apply()
            }

            // Navigate to the main shopping page
            val intent = Intent(requireActivity(), ShoppingActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish() // Finish the login activity so the user can't come back to it
        } else {
            showSnackbar("Invalid email or password")
        }
    }




}
private fun isValidCredentials(email: String, password: String): Boolean {
    // Implement your authentication logic here
    // For demonstration purposes, always return true
    return true
}

