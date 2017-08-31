package com.ddubson.loader

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun nycUrl(@Value("\${nyc.data.url}") url: String,
               @Value("\${nyc.data.limit}") limit: Int,
               @Value("\${nyc.data.appToken}") token: String): String {
        return "$url?\$limit=$limit&\$\$app_token=$token"
    }
}