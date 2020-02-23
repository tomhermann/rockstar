package com.zombietank.rockstar.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zombietank.rockstar.LabelFragment
import com.zombietank.rockstar.R
import com.zombietank.rockstar.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private typealias MenuId = Int

private const val SELECTED_KEY = "selection"

class NavigationActivity : AppCompatActivity() {
    private val navigationViewModel: NavigationViewModel by viewModel()
    private val sections: Map<MenuId, Section> by lazy {
        listOf(
            Section(sectionId = R.id.navigation_home, name = getString(R.string.title_home)) { NewsFragment() },
            Section(sectionId = R.id.navigation_dashboard, name = getString(R.string.title_dashboard)) { LabelFragment.newInstance(R.string.title_dashboard) },
            Section(sectionId = R.id.navigation_notifications, name = getString(R.string.title_notifications)) { LabelFragment.newInstance(R.string.title_notifications) }
        ).associateBy { it.sectionId }
    }

    private var selectedSection: Int? = null

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

        savedInstanceState
            ?.takeIf { it.containsKey(SELECTED_KEY) }
            ?.let { savedInstanceState.getInt(SELECTED_KEY) }
            ?.let { navigationViewModel.setSelectedNavigationItemId(it) }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        selectedSection?.let { outState.putInt(SELECTED_KEY, it) }
    }

    private fun selectSection(@IdRes sectionId: Int) {
        sections[sectionId]?.let { section ->
            if (section.isNotShowing()) {
                section.show()
            }
            supportActionBar?.title = section.name
            selectedSection = sectionId
        }
    }

    private fun Section.isNotShowing() =
        with(supportFragmentManager) {
            val content = findFragmentById(R.id.content)
            content == null || content != findFragmentByTag(tag)
        }

    private fun Section.show() {
        supportFragmentManager.commit {
            replace(R.id.content, fragmentProvider.invoke(), tag)
        }
    }

    private data class Section(
        @IdRes val sectionId: MenuId,
        val name: String,
        val fragmentProvider: () -> androidx.fragment.app.Fragment
    ) {
        val tag = sectionId.toString()
    }
}