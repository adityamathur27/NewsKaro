package com.example.newskaro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newskaro.Fragments.HomeFragment
import com.example.newskaro.Fragments.SavedFragment
import com.example.newskaro.Fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment =  HomeFragment()
        val savedFragment = SavedFragment()
        val searchFragment = SearchFragment()

        setCurrentFragment(homeFragment)

        val navBar : BottomNavigationView = findViewById(R.id.bottomNav)
        navBar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> setCurrentFragment(homeFragment)
                R.id.saved ->setCurrentFragment(savedFragment)
                R.id.search ->setCurrentFragment(searchFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flLayout , fragment)
            commit()
        }
    }
}