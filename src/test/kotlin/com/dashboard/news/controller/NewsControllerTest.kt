package com.dashboard.news.controller

import com.dashboard.news.contoller.NewsController
import com.dashboard.news.domain.model.Articles
import com.dashboard.news.domain.model.Sources
import com.dashboard.news.domain.service.NewsService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(NewsController::class)
class NewsControllerTest(@Autowired private val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var newsService: NewsService

    @Test
    fun `should headlines return all articles from service layer`() {
        // arrange
        val articles = Articles("OK", 0, emptyList())
        every { newsService.headlines() } returns articles

        // action
        val result = mockMvc.perform(get("/headlines") )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

        // assert
        assertEquals("{\"status\":\"OK\",\"totalResults\":0,\"articles\":[]}", result.response.contentAsString) // todo teste de retorno
        verify {
            newsService.headlines() // todo teste e comportamento
        }
    }

    @Test
    fun `should everything return all articles filtered by query from service layer`() {
        // arrange
        val articles = Articles("OK", 0, emptyList())
        every { newsService.everything("Futebol") } returns articles // todo mock especifico para a palavra Futebol

        // action
        val result = mockMvc.perform(get("/everything?query=Futebol") )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

        // assert
        assertEquals("{\"status\":\"OK\",\"totalResults\":0,\"articles\":[]}", result.response.contentAsString)
        verify {
            newsService.everything(any())
        }
    }

    @Test
    fun `should sources return all sources from service layer`() {
        // arrange
        val sources = Sources("OK", emptyList())
        every { newsService.sources() } returns sources

        // action
        val result = mockMvc.perform(get("/sources") )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()

        // assert
        assertEquals("{\"status\":\"OK\",\"sources\":[]}", result.response.contentAsString)
        verify {
            newsService.sources()
        }
    }
}