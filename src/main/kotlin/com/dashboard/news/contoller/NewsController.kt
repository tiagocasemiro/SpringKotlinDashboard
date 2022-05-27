package com.dashboard.news.contoller

import com.dashboard.news.domain.model.Articles
import com.dashboard.news.domain.model.Sources
import com.dashboard.news.domain.service.NewsService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsController(private val newsService: NewsService) {

    @RequestMapping("/headlines")
    fun headlines(): Articles {
        return newsService.headlines()
    }

    @RequestMapping("/everything")
    fun everything(@RequestParam query: String): Articles {
        return newsService.everything(query)
    }

    @RequestMapping("/sources")
    fun sources(): Sources {
        return newsService.sources()
    }
}