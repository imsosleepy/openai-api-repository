package com.openai.client


import com.openai.model.dto.openai.request.EmbeddingRequestDto
import com.openai.model.dto.openai.request.RequestChatDto
import com.openai.model.dto.openai.response.EmbeddingResponseDto
import com.openai.model.dto.openai.response.ResponseChatDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "OpenAiClient", url = "https://api.openai.com/v1", configuration = [OpenAiHeaderConfiguration::class])
interface OpenAiFeignClient {
    @PostMapping("/embeddings")
    fun createEmbedding(@RequestBody embeddingRequestDto: EmbeddingRequestDto): EmbeddingResponseDto?

    @PostMapping("/chat/completions")
    fun chatCompletion(@RequestBody requestChatDto: RequestChatDto): ResponseChatDto?
}