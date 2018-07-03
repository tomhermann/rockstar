package com.zombietank.rockstar.navigation

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.zombietank.rockstar.LabelFragment
import com.zombietank.rockstar.R
import com.zombietank.rockstar.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel

class NavigationActivity : AppCompatActivity() {
    private val navigationViewModel by viewModel<NavigationViewModel>()
    private val newsFragment = NewsFragment()
    private var selectedItemId: Int? = null

    private val sections: Map<Int, Section> = mapOf(
            R.id.navigation_home to Section(R.string.title_home) { newsFragment },
            R.id.navigation_dashboard to Section(R.string.title_dashboard) { LabelFragment.newInstance(R.string.title_dashboard) },
            R.id.navigation_notifications to Section(R.string.title_notifications) { LabelFragment.newInstance(R.string.title_notifications) }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            if (sections.containsKey(it.itemId)) {
                navigationViewModel.setSelectedNavigationItemId(it.itemId)
                return@OnNavigationItemSelectedListener true
            }
            false
        })

        navigationViewModel.getSelectedNavigationItemId().observe(this, Observer { it?.let { selectSection(it) } })
    }

    private fun selectSection(@IdRes sectionId: Int) {
        if (selectedItemId != sectionId || fragmentManager.findFragmentById(R.id.content) == null) {
            sections[sectionId]?.let { section ->
                supportActionBar?.title = getString(section.nameStringRes)

                supportFragmentManager.beginTransaction()
                        .replace(R.id.content, section.fragmentProvider.invoke())
                        .commit()
            }
        }
    }

    private data class Section(@StringRes val nameStringRes: Int, val fragmentProvider: () -> Fragment)
}


