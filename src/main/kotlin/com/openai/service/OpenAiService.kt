package com.openai.service

import com.openai.client.OpenAiFeignClient
import com.openai.model.dto.openai.ChatRequestDto
import com.openai.model.dto.openai.EmbeddingRequestDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class OpenAiService(
        private val openAiFeignClient: OpenAiFeignClient
) {
    val embeddingModel: String = "text-embedding-ada-002"

    val chatModel: String = "gpt-3.5-turbo-1106"

    fun createEmbedding(message: String): ResponseEntity<Any> {
        return try {
            val embeddingResult = openAiFeignClient.createEmbedding(EmbeddingRequestDto(
                    model = embeddingModel,
                    input = listOf(message)
            ))
            ResponseEntity.ok(embeddingResult)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun chatCompletion(message: String): ResponseEntity<Any> {
        return try {
            val chatResult = openAiFeignClient.chatCompletion(ChatRequestDto(
                    model = chatModel,
                    messages = listOf(
                            ChatRequestDto.ChatMessageVo("system", "You are chat bot. Only use Korean."),
                            ChatRequestDto.ChatMessageVo("user", message)),
                    maxTokens = 300,
                    temperature = 0.1,
                    stream = false
            ))
            ResponseEntity.ok(chatResult)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun uploadFile(file: MultipartFile): ResponseEntity<Any> {
        return try {
            val uploadResult = openAiFeignClient.uploadFile(file, "assistants")
            ResponseEntity.ok(uploadResult)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}