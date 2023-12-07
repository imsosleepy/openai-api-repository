package com.openai.client

import feign.RequestInterceptor
import feign.RequestTemplate
import feign.Retryer
import io.github.cdimascio.dotenv.Dotenv
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

class OpenAiHeaderConfiguration {
    @Bean
    fun requestInterceptor(): RequestInterceptor {
        val dotenv = Dotenv.load()
        return RequestInterceptor { requestTemplate: RequestTemplate ->
            requestTemplate.header("Authorization", "Bearer ${dotenv["OPENAI_API_KEY"]}")
        }
    }
}
