package com.ddubson.loader

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.support.ListItemReader
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.client.RestTemplate
import java.util.*
import javax.sql.DataSource


@SpringBootApplication
@EnableBatchProcessing
class Application(val jobBuilderFactory: JobBuilderFactory,
                  val stepBuilderFactory: StepBuilderFactory,
                  val nycUrl: String,
                  val dataSource: DataSource) {
    @Bean
    fun httpReader(): ListItemReader<ServiceRequest> {
        val restTemplate = RestTemplate()

        val serviceRequests: Array<ServiceRequest> = restTemplate.getForObject(nycUrl, Array<ServiceRequest>::class.java)

        serviceRequests.forEach { i ->
            println("[${Thread.currentThread().name}] item: $i")
        }

        return ListItemReader(serviceRequests.toList())
    }

    @Bean
    fun itemWriter(): JdbcBatchItemWriter<ServiceRequest> {
        val itemWriter = JdbcBatchItemWriter<ServiceRequest>()
        println("[${Thread.currentThread().name}] Writing items.")
        itemWriter.setDataSource(dataSource)
        itemWriter.setSql("INSERT INTO service_requests (uniqueKey, addressType, agency) VALUES (:uniqueKey, :addressType, :agency)")
        itemWriter.setItemSqlParameterSourceProvider(BeanPropertyItemSqlParameterSourceProvider<ServiceRequest>())
        itemWriter.afterPropertiesSet()

        return itemWriter
    }

    @Bean
    fun threadPoolTaskExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 5
        executor.maxPoolSize = 10
        executor.threadNamePrefix = "data-loader-"
        return executor
    }

    @Bean
    fun step1(): Step {
        return stepBuilderFactory.get("step1")
                .chunk<ServiceRequest, ServiceRequest>(2)
                .reader(httpReader())
                .writer(itemWriter())
                .faultTolerant()
                .skipPolicy(AlwaysSkipItemSkipPolicy())
                .taskExecutor(threadPoolTaskExecutor())
                .build()
    }

    @Bean
    fun job(): Job {
        return jobBuilderFactory.get("job-${UUID.randomUUID()}").start(step1()).build()
    }

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper().registerModule(KotlinModule())
}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}