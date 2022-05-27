package com.dashboard.news.repository.remote

import com.dashboard.news.domain.model.Articles
import com.dashboard.news.domain.model.Sources
import com.dashboard.news.repository.NewsRepository
import com.dashboard.news.repository.remote.client.NewsClientApi
import org.springframework.stereotype.Component

@Component
class NewsRepositoryImpl(private val newsClientApi: NewsClientApi): NewsRepository {

    override fun everything(query: String): Articles {
        return newsClientApi.everything(query)
    }

    override fun headlines(): Articles {
        return newsClientApi.headlines()
    }

    override fun sources(): Sources {
        return newsClientApi.sources()
    }
}