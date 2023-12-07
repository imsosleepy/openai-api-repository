package com.openai.service

import com.openai.client.OpenAiFeignClient
import com.openai.model.dto.openai.request.EmbeddingRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class OpenAiService (
        private val openAiFeignClient: OpenAiFeignClient
){
    val embeddingModel : String = "text-embedding-ada-002"

    fun createEmbedding(message: String) : ResponseEntity<Any> {

        val embeddingResult = openAiFeignClient.createEmbedding(EmbeddingRequestDto(
                model = embeddingModel,
                input = listOf(message)
        ))
        return ResponseEntity.ok(embeddingResult)
    }
}