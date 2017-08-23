package com.ddubson

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class App

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}