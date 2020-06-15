package com.zombietank.rockstar.news

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.zombietank.rockstar.*
import com.zombietank.rockstar.news.data.NewsArticle
import com.zombietank.rockstar.shadows.performSwipe
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.news_article_row.view.*
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.mockito.Answers
import org.mockito.Mock

class NewsFragmentTest : BaseRobolectricTest() {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var newsViewModel: NewsViewModel

    @Before
    fun setUp() {
        loadKoinModules(module { viewModel(override = true) { newsViewModel } })
    }

    @Test
    fun `load top stories on swipe to refresh`() {
        val scenario = launchFragmentInContainer<NewsFragment>()

        scenario.onFragment { it.swipeRefreshContainer.performSwipe() }

        verify(newsViewModel).loadTopStories()
    }

    @Test
    fun `show stories when they are observed`() {
        val stories = MutableLiveData<List<NewsArticle>>()
        whenever(newsViewModel.stories).thenReturn(stories)
        val scenario = launchFragmentInContainer<NewsFragment>()
        val article = NewsArticle(123L, "Hello World", "Tom", "Swell!", "http://tomhermann.me")

        stories.value = listOf(article)

        scenario.onFragment { fragment ->
            assertThat(fragment.newsList.adapter!!.itemCount, equalTo(1))
            assertThat(fragment.newsList.getChildAt(0).title.text.toString(), equalTo(article.title))
        }
    }

    @Test
    fun `update stories when they change`() {
        val stories = MutableLiveData<List<NewsArticle>>()
        whenever(newsViewModel.stories).thenReturn(stories)
        val scenario = launchFragmentInContainer<NewsFragment>()
        val article = NewsArticle(123L, "Hey Der.", "T", "Neat!.", "http://tomhermann.net")

        scenario.onFragment { fragment ->
            with(fragment.newsList.adapter!!) {
                stories.value = listOf(article)
                assertThat(itemCount, equalTo(1))

                stories.value = emptyList()
                assertThat(itemCount, equalTo(0))

                stories.value = listOf(article, article)
                assertThat(itemCount, equalTo(2))
            }
        }
    }
}
