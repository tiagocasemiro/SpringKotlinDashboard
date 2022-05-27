package com.dashboard.news.domain.service

import com.dashboard.news.domain.model.Article
import com.dashboard.news.domain.model.Articles
import com.dashboard.news.domain.model.Sources
import com.dashboard.news.repository.NewsRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.slot
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@WebMvcTest(NewsService::class)  // todo trocar a anotacao de inicializacao do container spring
@ExtendWith(SpringExtension::class)
class NewsServiceTest {
    @MockkBean
    lateinit var newsRepositoryMock: NewsRepository

    @Autowired
    lateinit var newsService: NewsService

    @Test
    fun `should headlines return all articles from repository`() {
        // arrange
        val articles = Articles("OK", 0, emptyList())
        every { newsRepositoryMock.headlines() } returns articles

        // action
        val result = newsService.headlines()

        // assert
        assertEquals("OK", result.status)
        assertEquals(0, result.totalResults)
        assertEquals(emptyList<Article>(), result.articles)
        verify {
            newsRepositoryMock.headlines()
        }
    }

    @Test
    fun `should everything call service with query and return all articles`() {
        // arrange
        val articles = Articles("OK", 0, emptyList())
        val querySlot = slot<String>() // todo - objeto usado para capturar parametros passados para mocks
        every { newsRepositoryMock.everything(capture(querySlot)) } returns articles // todo especificado o local da captura

        // action
        val result = newsService.everything("Futebol")

        // assert
        assertEquals("OK", result.status)
        assertEquals(0, result.totalResults)
        assertEquals(emptyList<Article>(), result.articles)
        assertEquals("Futebol", querySlot.captured) // todo varificacao do objeto capturado
        verify {
            newsRepositoryMock.everything(any())  // todo verificado a execucao da funcao, independente do parametro passado
            newsRepositoryMock.everything("Futebol") // todo verificacao a execucao da funcao com a palavra futebol
        }
    }

    @Test
    fun `should sources call service and return all sources of articles`() {
        // arrange
        val sources = Sources("OK", emptyList())
        every { newsRepositoryMock.sources() } returns sources

        // action
        val result = newsService.sources()

        // assert
        assertEquals("OK", result.status)
        assertEquals(emptyList<Sources>(), result.sources)
        verify {
            newsRepositoryMock.sources()
        }
    }
}