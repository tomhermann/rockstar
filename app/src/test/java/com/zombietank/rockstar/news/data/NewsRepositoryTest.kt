package com.zombietank.rockstar.news.data

import io.reactivex.rxjava3.core.Single
import org.junit.Test
import org.mockito.kotlin.isA
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.containsExactly
import strikt.assertions.isEmpty

class NewsRepositoryTest {
    private val dataSource: NewsDataSource = mock()
    private val newsRepository = NewsRepository(dataSource)

    @Test
    fun `can limit to specified count of top stories`() {
        val stories = generateStories(count = 3)
        configureDataSource(stories)

        val subscription = newsRepository.getTopStories(count = 2).test()

        subscription.assertValueCount(1).assertComplete()
        expectThat(subscription.values().flatten()).containsExactly(stories.take(2))
    }

    @Test
    fun `handles no stories`() {
        configureDataSource(generateStories(count = 0))

        val subscription = newsRepository.getTopStories(count = 10).test()

        subscription.assertValueCount(1).assertComplete()
        expectThat(subscription.values().flatten()).isEmpty()
    }

    @Test
    fun `cannot request negative stories`() {
        expectThrows<IllegalArgumentException> {
            newsRepository.getTopStories(count = -1).test()
        }
    }

    private fun configureDataSource(stories: List<NewsArticle>) =
        with(dataSource) {
            whenever(topStories).thenReturn(Single.just(stories.map { it.id }))

            whenever(getArticle(isA())).thenAnswer { invocation ->
                val index = invocation.arguments[0] as Long
                Single.just(stories[index.toInt()])
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