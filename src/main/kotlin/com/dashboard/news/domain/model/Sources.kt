package com.dashboard.news.domain.model

data class Sources (
	val status : String,
	val sources : List<Source>
)

data class Source (
	val id : String? = null,
	val name : String,
	val description : String? = null,
	val url : String? = null,
	val category : String? = null,
	val language : String? = null,
	val country : String? = null
)