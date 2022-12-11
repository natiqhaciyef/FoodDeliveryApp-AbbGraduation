package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.register

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
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : Fragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.sendButton.setOnClickListener {
            resetPassword(binding.userEmailInputTextForgotPassword.text.toString())
        }
    }

    private fun resetPassword(email: String){
        auth.sendPasswordResetEmail(email).addOnSuccessListener {

        }.addOnFailureListener {
            Snackbar.make(requireView(),"Something went wrong", Snackbar.LENGTH_LONG).show()
        }
    }
}