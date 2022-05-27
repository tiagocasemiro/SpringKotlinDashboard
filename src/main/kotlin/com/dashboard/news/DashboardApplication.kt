package com.dashboard.news

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class DashboardApplication

fun main(args: Array<String>) {
	runApplication<DashboardApplication>(*args)
}
