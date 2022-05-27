package com.dashboard.news.repository.remote.client

import com.dashboard.news.domain.model.Articles
import com.dashboard.news.domain.model.Sources
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "\${feign.google-news.name}", url = "\${feign.google-news.url}/v2")
interface NewsClientApi {

    @GetMapping("/everything")
    fun everything(@RequestParam(value="q") query: String, @RequestParam(value="language") country: String = "pt", @RequestParam(value="apiKey") apiKey: String = "71e188afa11b45f9998587517c6a6b93"): Articles

    @GetMapping("/top-headlines")
    fun headlines(@RequestParam(value="language") country: String = "pt", @RequestParam(value="apiKey") apiKey: String = "71e188afa11b45f9998587517c6a6b93"): Articles

    @GetMapping("/sources")
    fun sources(@RequestParam(value="country") country: String = "br", @RequestParam(value="apiKey") apiKey: String = "71e188afa11b45f9998587517c6a6b93"): Sources
}