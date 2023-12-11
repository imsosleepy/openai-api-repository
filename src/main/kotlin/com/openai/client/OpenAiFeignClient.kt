package com.openai.client


import com.openai.model.dto.openai.AssistantsRequestDto
import com.openai.model.dto.openai.ChatRequestDto
import com.openai.model.dto.openai.AssistantDto
import com.openai.model.dto.openai.EmbeddingRequestDto
import com.openai.model.dto.openai.EmbeddingResponseDto
import com.openai.model.dto.openai.ResponseChatDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "OpenAiClient", url = "https://api.openai.com/v1", configuration = [OpenAiHeaderConfiguration::class])
interface OpenAiFeignClient {
    @PostMapping("/embeddings")
    fun createEmbedding(@RequestBody embeddingRequestDto: EmbeddingRequestDto): EmbeddingResponseDto?

    @PostMapping("/chat/completions")
    fun chatCompletion(@RequestBody chatRequestDto: ChatRequestDto): ResponseChatDto?

    @PostMapping("/assistants")
    fun createAssistants(@RequestHeader("OpenAI-Beta") header: String, @RequestBody assistantsRequestDto: AssistantsRequestDto): AssistantDto?
}