package com.ddubson

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.explore.JobExplorer
import org.springframework.batch.core.launch.JobOperator
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
@EnableBatchProcessing
class App(val jobOperator: JobOperator, val jobExplorer: JobExplorer) {
    @EventListener
    fun startDataLoader(event: ContextRefreshedEvent) {
        if (jobExplorer.getJobInstanceCount("311-data") == 0) {
            jobOperator.start("311-data", "")
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(App::class.java, *args)
}