package com.openai.controller

import com.openai.model.dto.openai.AssistantCreateRequestDto
import com.openai.model.dto.openai.MessageRequestDto
import com.openai.service.AssistantService
import com.openai.service.OpenAiService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class OpenAiController(
        private val openAiService: OpenAiService
) {
    @PostMapping("/embedding")
    fun createEmbedding(@RequestBody messageRequestDto: MessageRequestDto)
            : ResponseEntity<Any> = openAiService.createEmbedding(messageRequestDto.message)

    @PostMapping("/chat")
    fun chatCompletion(@RequestBody messageRequestDto: MessageRequestDto)
            : ResponseEntity<Any> = openAiService.chatCompletion(messageRequestDto.message)
}