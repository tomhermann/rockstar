package com.zombietank.rockstar.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zombietank.rockstar.LabelFragment
import com.zombietank.rockstar.R
import com.zombietank.rockstar.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val SELECTED_KEY = "selection"

class NavigationActivity : AppCompatActivity() {
    private val navigationViewModel by viewModel<NavigationViewModel>()
    private var selectedSection: Int? = null

    private val sections: Map<Int, Section> = mapOf(
            R.id.navigation_home to Section(R.string.title_home) { NewsFragment() },
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

        savedInstanceState?.takeIf { it.containsKey(SELECTED_KEY) }
                ?.let { savedInstanceState.getInt(SELECTED_KEY) }
                ?.let { navigationViewModel.setSelectedNavigationItemId(it) }
    }

    private fun selectSection(@IdRes sectionId: Int) {
        sections[sectionId]?.let { section ->
            val name = getString(section.nameStringRes)
            supportActionBar?.title = name

            val contentFragment = supportFragmentManager.findFragmentById(R.id.content)
            if (contentFragment == null || contentFragment != supportFragmentManager.findFragmentByTag(name)) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.content, section.fragmentProvider.invoke(), name)
                        .commit()
            }
            selectedSection = sectionId
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        selectedSection?.let { outState?.putInt(SELECTED_KEY, it) }
    }

    private data class Section(@StringRes val nameStringRes: Int, val fragmentProvider: () -> androidx.fragment.app.Fragment)
}


