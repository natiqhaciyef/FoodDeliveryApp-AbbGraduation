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
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.FragmentRegisterBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home.MainActivity
import kotlin.collections.Map as Map1


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        firestore = Firebase.firestore

        if (auth.currentUser != null){
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

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
            val usernames = hashMapOf<String, String>()
            usernames["name"] = username
            firestore.collection("UserNames").document(auth.currentUser!!.uid).set(usernames).addOnSuccessListener {
                val intent = Intent(requireActivity(), MainActivity::class.java)
                startActivity(intent)
            }
        }.addOnFailureListener {
            Snackbar.make(requireView(),"Something went wrong", Snackbar.LENGTH_LONG).show()
        }
    }
}