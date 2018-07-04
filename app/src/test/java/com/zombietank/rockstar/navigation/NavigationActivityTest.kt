package com.zombietank.rockstar.navigation

import android.support.v4.app.Fragment
import com.zombietank.rockstar.LabelFragment
import com.zombietank.rockstar.R
import com.zombietank.rockstar.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.hamcrest.Matcher
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NavigationActivityTest {

    @Test
    fun initialViewIsNewsScreen() {
        val activity = Robolectric.setupActivity(NavigationActivity::class.java)

        assertThat(activity.supportActionBar?.title.toString(), equalTo(activity.getString(R.string.title_home)))
        verifyContentOf(activity, instanceOf(NewsFragment::class.java))
    }

    @Test
    fun contentChangesToDashboardOnSelection() {
        val activity = Robolectric.setupActivity(NavigationActivity::class.java)

        activity.navigation.selectedItemId = R.id.navigation_dashboard

        assertThat(activity.supportActionBar?.title.toString(), equalTo(activity.getString(R.string.title_dashboard)))
        verifyContentOf(activity, instanceOf(LabelFragment::class.java))
    }

    @Test
    fun selectedScreenIsStillShownOnConfigurationChange() {
        val activity = Robolectric.setupActivity(NavigationActivity::class.java)
        activity.navigation.selectedItemId = R.id.navigation_dashboard

        activity.recreate()

        assertThat(activity.supportActionBar?.title.toString(), equalTo(activity.getString(R.string.title_dashboard)))
        verifyContentOf(activity, instanceOf(LabelFragment::class.java))
    }

    private fun verifyContentOf(activity: NavigationActivity, matches: Matcher<Fragment>?) {
        val fragmentManager = activity.supportFragmentManager
        assertThat(fragmentManager.findFragmentById(R.id.content), matches)
    }
}
