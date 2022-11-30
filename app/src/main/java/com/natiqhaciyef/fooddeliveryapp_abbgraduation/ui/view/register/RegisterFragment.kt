package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.FragmentRegisterBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home.MainActivity


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.registerButton.setOnClickListener {
            val email = binding.userEmailInputTextRegister.text.toString()
            val password = binding.userPasswordInputTextRegister.text.toString()
            val username = binding.userNameInputTextRegister.text.toString()
            registerButtonAction(username, email, password)
        }

        binding.goToLogin.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.loginFragment)
        }
    }

    private fun registerButtonAction(username: String, email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }.addOnFailureListener {
            Snackbar.make(requireView(),"Something went wrong", Snackbar.LENGTH_LONG).show()
        }
    }
}