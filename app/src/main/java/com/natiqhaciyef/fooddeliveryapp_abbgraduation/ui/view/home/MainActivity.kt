package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.view.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_tablayout.*

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

        binding.toolbarInclude.cartIcon.setOnClickListener {
            navigationFragments(CartFragment())
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

    private fun navigationFragments(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.navHostView,fragment)
            commit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.homeFragment -> navigationFragments(HomeFragment())
            R.id.cartFragment -> navigationFragments(CartFragment())
            R.id.paymentFragment -> navigationFragments(PaymentFragment())
            else -> false
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}