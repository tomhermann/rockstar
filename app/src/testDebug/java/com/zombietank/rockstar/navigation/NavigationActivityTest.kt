package com.zombietank.rockstar.navigation

import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.launchActivity
import com.zombietank.rockstar.BaseRobolectricTest
import com.zombietank.rockstar.LabelFragment
import com.zombietank.rockstar.R
import com.zombietank.rockstar.isEqualToResource
import com.zombietank.rockstar.news.NewsFragment
import com.zombietank.rockstar.news.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.Answers
import org.mockito.Mock
import strikt.api.Assertion
import strikt.api.expectThat

class NavigationActivityTest : BaseRobolectricTest() {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var newsViewModel: NewsViewModel

    @Before
    fun setUp() {
        loadKoinModules(module { viewModel(override = true) { newsViewModel } })
    }
    @Test
    fun `initial view is news screen`() {
        launchActivity<NavigationActivity>().onActivity { activity ->
            expectThat(activity.supportActionBar?.title).isEqualToResource(R.string.title_home)
            expectThat(activity).contentIsA<NewsFragment>()
        }
    }

    @Test
    fun `content changes to dashboard on selection`() {
        launchActivity<NavigationActivity>().onActivity { activity ->
            activity.navigation.selectedItemId = R.id.navigation_dashboard

            expectThat(activity.supportActionBar?.title).isEqualToResource(R.string.title_dashboard)
            expectThat(activity).contentIsA<LabelFragment>()
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
            expectThat(activity).contentIsA<LabelFragment>()
        }
    }

    private inline fun <reified T> Assertion.Builder<out AppCompatActivity>.contentIsA() =
        assert("is instance of %s", T::class.qualifiedName) { actual ->
            val contentFragment = actual.supportFragmentManager.findFragmentById(R.id.content)
            if (T::class.isInstance(contentFragment)) {
                pass()
            } else {
                fail(contentFragment?.javaClass)
            }
        }
}
