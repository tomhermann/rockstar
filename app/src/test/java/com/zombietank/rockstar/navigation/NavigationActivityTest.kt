package com.zombietank.rockstar.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.test.core.app.launchActivity
import com.zombietank.rockstar.BaseRobolectricTest
import com.zombietank.rockstar.LabelFragment
import com.zombietank.rockstar.R
import com.zombietank.rockstar.isEqualToResource
import com.zombietank.rockstar.news.NewsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Test
import strikt.api.Assertion
import strikt.api.expectThat
import kotlin.reflect.KClass

class NavigationActivityTest : BaseRobolectricTest() {

    @Test
    fun `initial view is news screen`() {
        launchActivity<NavigationActivity>().onActivity { activity ->
            expectThat(activity.supportActionBar?.title).isEqualToResource(R.string.title_home)
            expectThat(activity).contentIsA(NewsFragment::class)
        }
    }

    @Test
    fun `content changes to dashboard on selection`() {
        launchActivity<NavigationActivity>().onActivity { activity ->
            activity.navigation.selectedItemId = R.id.navigation_dashboard

            expectThat(activity.supportActionBar?.title).isEqualToResource(R.string.title_dashboard)
            expectThat(activity).contentIsA(LabelFragment::class)
        }
    }

    @Test
    fun `selected screen is still shown on configuration change`() {
        val scenario = launchActivity<NavigationActivity>()
        scenario.onActivity { activity ->
            activity.navigation.selectedItemId = R.id.navigation_notifications
        }

        scenario.recreate()

        scenario.onActivity { activity ->
            expectThat(activity.supportActionBar?.title).isEqualToResource(R.string.title_notifications)
            expectThat(activity).contentIsA(LabelFragment::class)
        }
    }

    private fun Assertion.Builder<out AppCompatActivity>.contentIsA(clazz: KClass<out Fragment>) =
        assert("is instance of %s", clazz.qualifiedName) { actual ->
            val contentFragment = actual.supportFragmentManager.findFragmentById(R.id.content)
            if (clazz.isInstance(contentFragment)) {
                pass()
            } else {
                fail(contentFragment?.javaClass)
            }
        }
}
