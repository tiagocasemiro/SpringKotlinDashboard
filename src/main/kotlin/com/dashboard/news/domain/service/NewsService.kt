package com.dashboard.news.domain.service

import com.dashboard.news.domain.model.Articles
import com.dashboard.news.domain.model.Sources
import com.dashboard.news.repository.NewsRepository
import org.springframework.stereotype.Service

@Service
class NewsService(private val newsRepository: NewsRepository) {

    fun everything(query: String): Articles {
        return newsRepository.everything(query)
    }

    fun headlines(): Articles {
        return newsRepository.headlines()
    }

    fun sources(): Sources {
        return newsRepository.sources()
    }
}