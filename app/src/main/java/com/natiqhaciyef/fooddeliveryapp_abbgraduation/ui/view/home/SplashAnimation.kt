package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.ActivitySplashAnimationBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.register.RegisterActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashAnimation : AppCompatActivity() {
    private lateinit var binding: ActivitySplashAnimationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.Main).launch {
            delay(3000)
            navigateToRegister()
        }
    }

    private fun navigateToRegister(){
        val intent = Intent(this@SplashAnimation, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }
}