package com.dashboard.news.repository

import com.dashboard.news.domain.model.Articles
import com.dashboard.news.domain.model.Sources

interface NewsRepository {
    fun everything(query: String): Articles
    fun headlines(): Articles
    fun sources(): Sources
}