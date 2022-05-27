package com.dashboard.news.repository.remote

import com.dashboard.news.domain.model.Article
import com.dashboard.news.domain.model.Articles
import com.dashboard.news.domain.model.Sources
import com.dashboard.news.repository.NewsRepository
import com.dashboard.news.repository.remote.client.NewsClientApi
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NewsRepositoryImplTest {
    private val newsClientApiMock: NewsClientApi = mockk() // todo criacao do mock na mao
    private val newsRepository: NewsRepository = NewsRepositoryImpl(newsClientApiMock) // todo inicializacao do objeto de teste na mao

    @Test
    fun `should headlines return all articles`() {
        // arrange
        val articles = Articles("OK", 0, emptyList())
        every { newsClientApiMock.headlines() } returns articles

        // action
        val result = newsRepository.headlines()

        // assert
        assertEquals("OK", result.status)
        assertEquals(0, result.totalResults)
        assertEquals(emptyList<Article>(), result.articles)
        verify {
            newsClientApiMock.headlines()
        }
    }

    @Test
    fun `should everything call service with query and return all articles`() {
        // arrange
        val articles = Articles("OK", 0, emptyList())
        every { newsClientApiMock.everything("Futebol") } returns articles

        // action
        val result = newsRepository.everything("Futebol")

        // assert
        assertEquals("OK", result.status)
        assertEquals(0, result.totalResults)
        assertEquals(emptyList<Article>(), result.articles)
        verify {
            newsClientApiMock.everything("Futebol")
        }
    }

    @Test
    fun `should sources call service and return all sources of articles`() {
        // arrange
        val sources = Sources("OK", emptyList())
        every { newsClientApiMock.sources() } returns sources

        // action
        val result = newsRepository.sources()

        // assert
        assertEquals("OK", result.status)
        assertEquals(emptyList<Sources>(), result.sources)
        verify {
            newsClientApiMock.sources()
        }
    }
}