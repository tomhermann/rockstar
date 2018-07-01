package com.zombietank.rockstar

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.zombietank.rockstar.dashboard.DashboardFragment
import com.zombietank.rockstar.news.NewsFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.content, NewsFragment()).commit()
        }

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                navigation.selectedItemId -> {
                    return@OnNavigationItemSelectedListener false
                }
                R.id.navigation_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.content, NewsFragment()).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    supportFragmentManager.beginTransaction().replace(R.id.content, DashboardFragment()).commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}

