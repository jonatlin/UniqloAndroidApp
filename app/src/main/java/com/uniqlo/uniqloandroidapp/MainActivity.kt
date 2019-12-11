package com.uniqlo.uniqloandroidapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_appbar.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController: NavController = findNavController(R.id.nav_host)

       /* main_appbar.toolbar.run {

            inflateMenu(R.menu.nav_top_menu)
            setOnMenuItemClickListener()
            { item ->
                if (item.itemId == R.id.search) {
                    Toast.makeText(context, "TODO: Create Search Dialog", Toast.LENGTH_SHORT).show()
                    true
                } else {
                    false
                }

            }

        }*/

        setupBottomNavMenu(navController)
        setSupportActionBar(findViewById(R.id.main_appbar))
    }


    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav?.setupWithNavController(navController)
    }
}
