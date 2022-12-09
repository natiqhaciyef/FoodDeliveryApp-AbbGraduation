package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.ActivityMainBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.AlertDialogLayoutBinding
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.register.RegisterActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_tablayout.*
import kotlinx.android.synthetic.main.app_bar_tablayout.view.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        firestore = Firebase.firestore

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.navView, navHostFragment.navController)

        val toogle = ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, 0, 0)
        toogle.drawerArrowDrawable.color = this.getColor(R.color.mainWhite)
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.isDrawerIndicatorEnabled = true
        toogle.syncState()

        toolbarInclude.cartIcon.setOnClickListener {
            navigationFragments(R.id.cartFragment)
        }

        navView.setNavigationItemSelectedListener(this)

    }

    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun navigationFragments(id: Int){
        binding.navHostView.findNavController().navigate(id)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.homeFragment -> navigationFragments(R.id.homeFragment)
            R.id.cartFragment -> navigationFragments(R.id.cartFragment)
            R.id.paymentFragment -> navigationFragments(R.id.paymentFragment)
            R.id.savedFoodsFragment -> navigationFragments(R.id.likedFoodsFragment)
            R.id.logOut -> showAlertDialog()
            else -> false
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun signOut(){
        auth.signOut()
        val intent = Intent(this@MainActivity , RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showAlertDialog() {
        val view = AlertDialogLayoutBinding.inflate(layoutInflater)
        val positiveButton = view.buttonActionPositive
        val negativeButton = view.buttonActionNegative

        val customAlertDialog = AlertDialog.Builder(this)
            .setView(view.root)
            .create()

        positiveButton.setOnClickListener {
            signOut()
            customAlertDialog.cancel()
        }

        negativeButton.setOnClickListener {
            customAlertDialog.cancel()
        }

        customAlertDialog.show()
    }
}