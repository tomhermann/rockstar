package com.zombietank.rockstar.navigation

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.zombietank.rockstar.BaseRobolectricTest
import com.zombietank.rockstar.LabelFragment
import com.zombietank.rockstar.R
import com.zombietank.rockstar.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment

class NavigationActivityTest : BaseRobolectricTest() {

    @Test
    fun initialViewIsNewsScreen() {
        val activity = Robolectric.setupActivity(NavigationActivity::class.java)

        assertThat(activity.supportActionBar?.title, equalToString(R.string.title_home))
        verifyContentOf(activity, instanceOf(NewsFragment::class.java))
    }

    @Test
    fun contentChangesToDashboardOnSelection() {
        val activity = Robolectric.setupActivity(NavigationActivity::class.java)

        activity.navigation.selectedItemId = R.id.navigation_dashboard

        assertThat(activity.supportActionBar?.title, equalToString(R.string.title_dashboard))
        verifyContentOf(activity, instanceOf(LabelFragment::class.java))
    }

    @Test
    fun selectedScreenIsStillShownOnConfigurationChange() {
        val activity = Robolectric.setupActivity(NavigationActivity::class.java)
        activity.navigation.selectedItemId = R.id.navigation_notifications

        activity.recreate()

        assertThat(activity.supportActionBar?.title, equalToString(R.string.title_notifications))
        verifyContentOf(activity, instanceOf(LabelFragment::class.java))
    }

    private fun verifyContentOf(activity: NavigationActivity, matches: Matcher<androidx.fragment.app.Fragment?>) {
        val fragmentManager = activity.supportFragmentManager
        assertThat(fragmentManager.findFragmentById(R.id.content), matches)
    }

    private fun equalToString(@StringRes stringResId: Int): Matcher<CharSequence?> {
        return equalTo(RuntimeEnvironment.application.getString(stringResId))
    }
}
