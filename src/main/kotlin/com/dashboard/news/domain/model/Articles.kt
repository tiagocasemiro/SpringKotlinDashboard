package com.dashboard.news.domain.model

data class Articles (
	val status : String,
	val totalResults : Int,
	val articles : List<Article>
)

data class Article (
	val source : Source? = null,
	val author : String? = null,
	val title : String? = null,
	val description : String? = null,
	val url : String? = null,
	val urlToImage : String? = null,
	val publishedAt : String? = null,
	val content : String? = null
)