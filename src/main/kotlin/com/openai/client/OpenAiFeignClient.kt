package com.openai.client


import com.openai.client.header.OpenAiHeaderConfiguration
import com.openai.model.dto.openai.ChatRequestDto
import com.openai.model.dto.openai.EmbeddingRequestDto
import com.openai.model.dto.openai.EmbeddingResponseDto
import com.openai.model.dto.openai.ResponseChatDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "OpenAiClient", url = "https://api.openai.com/v1", configuration = [OpenAiHeaderConfiguration::class])
interface OpenAiFeignClient {
    @PostMapping("/embeddings")
    fun createEmbedding(@RequestBody embeddingRequestDto: EmbeddingRequestDto): EmbeddingResponseDto?

    @PostMapping("/chat/completions")
    fun chatCompletion(@RequestBody chatRequestDto: ChatRequestDto): ResponseChatDto?
}