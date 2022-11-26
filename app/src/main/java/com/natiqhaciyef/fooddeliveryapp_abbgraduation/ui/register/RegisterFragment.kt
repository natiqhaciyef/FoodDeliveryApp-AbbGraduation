package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.FragmentRegisterBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.home.MainActivity


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            val email = binding.userEmailInputTextRegister.text.toString()
            val password = binding.userPasswordInputTextRegister.text.toString()
            val username = binding.userNameInputTextRegister.text.toString()
            registerButtonAction(username, email, password)
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