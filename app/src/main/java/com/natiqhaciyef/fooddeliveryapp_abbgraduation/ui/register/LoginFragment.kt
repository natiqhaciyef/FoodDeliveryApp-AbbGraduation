package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.FragmentLoginBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.home.MainActivity


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.loginButton.setOnClickListener {
            val email = binding.userEmailInputTextLogin.text.toString()
            val password = binding.userPasswordInputTextLogin.text.toString()
            loginButtonAction(email, password)
        }
    }

    private fun loginButtonAction(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }.addOnFailureListener {
            Snackbar.make(requireView(),"Something went wrong",Snackbar.LENGTH_LONG).show()
        }
    }
}