package com.zombietank.rockstar.navigation

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.zombietank.rockstar.ActivityScenarioSupport.launch
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

class NavigationActivityTest : BaseRobolectricTest() {

    @Test
    fun initialViewIsNewsScreen() {
        launch<NavigationActivity> { scenario ->
            scenario.onActivity { activity ->
                assertThat(activity.supportActionBar?.title, equalToStringRes(R.string.title_home))
                verifyContentOf(activity, instanceOf(NewsFragment::class.java))
            }
        }
    }

    @Test
    fun contentChangesToDashboardOnSelection() {
        launch<NavigationActivity> { scenario ->
            scenario.onActivity { activity ->
                activity.navigation.selectedItemId = R.id.navigation_dashboard

                assertThat(activity.supportActionBar?.title, equalToStringRes(R.string.title_dashboard))
                verifyContentOf(activity, instanceOf(LabelFragment::class.java))
            }
        }
    }

    @Test
    fun selectedScreenIsStillShownOnConfigurationChange() {
        launch<NavigationActivity> { scenario ->
            scenario.onActivity { activity ->
                activity.navigation.selectedItemId = R.id.navigation_notifications
            }

            scenario.recreate()

            scenario.onActivity { activity ->
                assertThat(activity.supportActionBar?.title, equalToStringRes(R.string.title_notifications))
                verifyContentOf(activity, instanceOf(LabelFragment::class.java))
            }
        }
    }

    private fun verifyContentOf(activity: NavigationActivity, matches: Matcher<Fragment?>) =
        assertThat(activity.supportFragmentManager.findFragmentById(R.id.content), matches)


    private fun equalToStringRes(@StringRes stringResId: Int): Matcher<CharSequence?> =
        equalTo(applicationContext().getString(stringResId))
}
