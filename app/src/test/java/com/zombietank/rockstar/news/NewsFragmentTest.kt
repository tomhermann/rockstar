package com.zombietank.rockstar.news

import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.zombietank.rockstar.BaseRobolectricTest
import com.zombietank.rockstar.RecyclerViewHelper
import com.zombietank.rockstar.ShadowSwipeRefreshLayout
import com.zombietank.rockstar.SupportFragmentController
import com.zombietank.rockstar.news.data.NewsArticle
import kotlinx.android.synthetic.main.fragment_news.*
import kotlinx.android.synthetic.main.news_article_row.view.*
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.loadKoinModules
import org.mockito.Answers
import org.mockito.Mock
import org.robolectric.shadow.api.Shadow

class NewsFragmentTest : BaseRobolectricTest() {
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var newsViewModel: NewsViewModel

    @Before
    fun setUp() {
        loadKoinModules(module {viewModel(override = true) { newsViewModel } })
    }

    @Test
    fun `load top stories on swipe to refresh`() {
        val controller = SupportFragmentController.of(NewsFragment()).create().start().resume().visible()
        val swipeRefreshContainer = controller.get().swipeRefreshContainer

        Shadow.extract<ShadowSwipeRefreshLayout>(swipeRefreshContainer).onRefreshListener?.onRefresh()

        verify(newsViewModel).loadTopStories()
    }

    @Test
    fun `show stories when they are observed`() {
        val stories = MutableLiveData<List<NewsArticle>>()
        whenever(newsViewModel.stories).thenReturn(stories)
        val controller = SupportFragmentController.of(NewsFragment()).create().start().resume().visible()
        val article = NewsArticle(123L, "Hello World", "Tom", "Swell!", "http://tomhermann.me")

        stories.value = listOf(article)

        val newsList = RecyclerViewHelper.layout(controller.get().newsList)
        assertThat(newsList.adapter?.itemCount, equalTo(1))
        assertThat(newsList.getChildAt(0).title.text.toString(), equalTo(article.title))
    }

    @Test
    fun `update stories when they change`() {
        val stories = MutableLiveData<List<NewsArticle>>()
        whenever(newsViewModel.stories).thenReturn(stories)
        val controller = SupportFragmentController.of(NewsFragment()).create().start().resume().visible()
        val article = NewsArticle(123L, "Hey Der.", "T", "Neat!.", "http://tomhermann.net")
        val newsList = RecyclerViewHelper.layout(controller.get().newsList)

        stories.value = listOf(article)
        assertThat(newsList.adapter?.itemCount, equalTo(1))

        stories.value = listOf()
        assertThat(newsList.adapter?.itemCount, equalTo(0))

        stories.value = listOf(article, article)
        assertThat(newsList.adapter?.itemCount, equalTo(2))
    }
}
