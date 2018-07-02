package com.zombietank.rockstar

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.zombietank.rockstar.news.NewsFragment
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var sections: Map<Int, Section>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sections = buildSections()
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            sections[R.id.navigation_home]?.let { home ->
                selectSection(home)
            }
        }

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            if (navigation.selectedItemId == it.itemId) {
                return@OnNavigationItemSelectedListener false
            }

            sections[it.itemId]?.let {
                selectSection(it)
                return@OnNavigationItemSelectedListener true
            }
            false
        })
    }

    private fun buildSections(): Map<Int, Section> =
        mapOf(
                R.id.navigation_home to Section(getString(R.string.title_home)) { NewsFragment() },
                R.id.navigation_dashboard to Section(getString(R.string.title_dashboard)) { SimpleLabelFragment.newInstance(R.string.title_dashboard) },
                R.id.navigation_notifications to Section(getString(R.string.title_notifications)) { SimpleLabelFragment.newInstance(R.string.title_notifications) }
        )

    private fun selectSection(section: Section) {
        supportActionBar?.title = section.name
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, section.fragmentProvider.invoke())
                .commit()
    }

    private data class Section(val name: String, val fragmentProvider: () -> Fragment)
}

