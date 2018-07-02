package com.zombietank.rockstar

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.zombietank.rockstar.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val newsFragment = NewsFragment()
    private val sections: Map<Int, Section> = mapOf(
            R.id.navigation_home to Section(R.string.title_home) { newsFragment },
            R.id.navigation_dashboard to Section(R.string.title_dashboard) { LabelFragment.newInstance(R.string.title_dashboard) },
            R.id.navigation_notifications to Section(R.string.title_notifications) { LabelFragment.newInstance(R.string.title_notifications) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            sections[R.id.navigation_home]?.let { selectSection(it) }
        }

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            if (navigation.selectedItemId == it.itemId) {
                return@OnNavigationItemSelectedListener false
            }

            sections[it.itemId]?.let { section ->
                selectSection(section)
                return@OnNavigationItemSelectedListener true
            }
            false
        })
    }

    private fun selectSection(section: Section) {
        supportActionBar?.title = getString(section.nameStringRes)
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, section.fragmentProvider.invoke())
                .commit()
    }

    private data class Section(@StringRes val nameStringRes: Int, val fragmentProvider: () -> Fragment)
}

