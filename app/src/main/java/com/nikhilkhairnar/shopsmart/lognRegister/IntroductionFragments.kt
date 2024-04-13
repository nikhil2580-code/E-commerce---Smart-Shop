package com.nikhilkhairnar.shopsmart.lognRegister

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.nikhilkhairnar.shopsmart.R



class IntroductionFragments : Fragment() {
    private lateinit var navController: NavController


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_introduction_fragments, container,false)

        val signInButton = rootView.findViewById<Button>(R.id.button_sign_in)
        signInButton.setOnClickListener {
            navController.navigate(R.id.action_introductionFragments_to_loginFragment3)
        }
        val signUpButton = rootView.findViewById<Button>(R.id.button_signup)
        signUpButton.setOnClickListener {
            navController.navigate(R.id.action_introductionFragments_to_signUpFragment2)
        }


        return  rootView
    }




}
