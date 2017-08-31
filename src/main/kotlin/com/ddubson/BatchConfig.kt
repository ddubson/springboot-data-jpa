package com.ddubson

import com.ddubson.models.ServiceRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.JobRegistry
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor
import org.springframework.batch.core.converter.DefaultJobParametersConverter
import org.springframework.batch.core.explore.JobExplorer
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.JobOperator
import org.springframework.batch.core.launch.support.SimpleJobOperator
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.skip.AlwaysSkipItemSkipPolicy
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider
import org.springframework.batch.item.database.JdbcBatchItemWriter
import org.springframework.batch.item.support.ListItemReader
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.client.RestTemplate
import javax.sql.DataSource

@Configuration
class BatchConfig(val jobBuilderFactory: JobBuilderFactory,
                  val stepBuilderFactory: StepBuilderFactory,
                  val nycUrl: String,
                  val dataSource: DataSource): ApplicationContextAware {
    private lateinit var appContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.appContext = applicationContext
    }

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
        itemWriter.setSql("""
            INSERT INTO service_requests
                (uniqueKey, createdDate, closedDate, addressType, agencyName, agency)
            VALUES
                (:uniqueKey, :createdDate, :closedDate, :addressType, :agencyName, :agency)
            """)
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

    @Bean("311-data")
    fun job(): Job = jobBuilderFactory.get("311-data").start(step1()).build()

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate = builder.build()

    @Bean
    fun objectMapper(): ObjectMapper = ObjectMapper().registerModule(KotlinModule())

    @Bean
    fun jobRegistrar(jobRegistry: JobRegistry): JobRegistryBeanPostProcessor {
        val registrar = JobRegistryBeanPostProcessor()
        registrar.setJobRegistry(jobRegistry)
        registrar.setBeanFactory(appContext.autowireCapableBeanFactory)
        registrar.afterPropertiesSet()
        return registrar
    }

    @Bean
    fun jobOperator(jobRegistry: JobRegistry,
                    jobLauncher: JobLauncher,
                    jobExplorer: JobExplorer,
                    jobRepository: JobRepository): JobOperator {
        val jobOperator = SimpleJobOperator()
        jobOperator.setJobLauncher(jobLauncher)
        jobOperator.setJobParametersConverter(DefaultJobParametersConverter())
        jobOperator.setJobRegistry(jobRegistry)
        jobOperator.setJobExplorer(jobExplorer)
        jobOperator.setJobRepository(jobRepository)
        jobOperator.afterPropertiesSet()

        return jobOperator
    }
}