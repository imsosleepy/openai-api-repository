package com.openai.service

import com.openai.client.OpenAiFeignClient
import com.openai.model.dto.openai.request.EmbeddingRequestDto
import com.openai.model.dto.openai.request.RequestChatDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class OpenAiService (
        private val openAiFeignClient: OpenAiFeignClient
){
    val embeddingModel: String = "text-embedding-ada-002"

    val chatModel: String = "gpt-3.5-turbo-1106"

    fun createEmbedding(message: String) : ResponseEntity<Any> {
        val embeddingResult = openAiFeignClient.createEmbedding(EmbeddingRequestDto(
                model = embeddingModel,
                input = listOf(message)
        ))
        return ResponseEntity.ok(embeddingResult)
    }

    fun chatCompletion(message: String) : ResponseEntity<Any> {
        val chatResult = openAiFeignClient.chatCompletion(RequestChatDto(
                model = chatModel,
                messages = listOf(
                        RequestChatDto.ChatMessageVo("system", "You are chat bot. Only use Korean."),
                        RequestChatDto.ChatMessageVo("user", message)),
                maxTokens = 300,
                temperature = 0.1,
                stream = false
        ))
        return ResponseEntity.ok(chatResult)
    }
}