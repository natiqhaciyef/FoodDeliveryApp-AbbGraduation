package com.natiqhaciyef.fooddeliveryapp_abbgraduation.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.R
import com.natiqhaciyef.fooddeliveryapp_abbgraduation.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_tablayout.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener  {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostView) as NavHostFragment
        NavigationUI.setupWithNavController(binding.navView, navHostFragment.navController)

        val toogle = ActionBarDrawerToggle(this, binding.drawerLayout, toolbar, 0, 0)
        toogle.drawerArrowDrawable.color = this.getColor(R.color.white)
        binding.drawerLayout.addDrawerListener(toogle)
        toogle.isDrawerIndicatorEnabled = true
        toogle.syncState()

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