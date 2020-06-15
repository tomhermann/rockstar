package com.zombietank.rockstar.news.data

import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.containsExactly
import strikt.assertions.isEmpty

class NewsRepositoryTest {
    private val dataSource: NewsDataSource = mock()
    private val newsRepository = NewsRepository(dataSource)

    @Test
    fun `can limit to specified count of top stories`() {
        val stories = generateStories(11)
        configureDataSource(stories)

        val subscription = newsRepository.getTopStories(count = 10).test()

        subscription.assertValueCount(1).assertComplete()
        expectThat(subscription.values()[0]).containsExactly(stories.take(10))
    }

    @Test
    fun `handles no stories`() {
        configureDataSource(generateStories(count = 0))

        val subscription = newsRepository.getTopStories(count = 10).test()

        subscription.assertValueCount(1).assertComplete()
        expectThat(subscription.values()[0]).isEmpty()
    }

    @Test
    fun `cannot request negative stories`() {
        expectThrows<IllegalArgumentException> {
            newsRepository.getTopStories(count = -1).test()
        }
    }

    private fun configureDataSource(stories: List<NewsArticle>) {
        whenever(dataSource.topStories).thenReturn(Single.just(stories.map { it.id }))
        whenever(dataSource.getArticle(isA())).thenAnswer { invocation ->
            Single.just(stories[(invocation.arguments[0] as Long).toInt()])
        }
    }

    private fun generateStories(count: Int): List<NewsArticle> =
        (0 until count)
            .map { id ->
                NewsArticle(
                    id = id.toLong(),
                    title = "Title $id",
                    by = "Awesome Author",
                    description = "Hello World, $id",
                    url = "http://example.com/foo?id=$id"
                )
            }

}