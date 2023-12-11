package com.openai.controller

import com.openai.model.dto.openai.AssistantCreateRequestDto
import com.openai.model.dto.openai.MessageRequestDto
import com.openai.service.AssistantService
import com.openai.service.OpenAiService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OpenAiController(
        private val openAiService: OpenAiService,
        private val assistantService: AssistantService
) {
    @PostMapping("/embedding")
    fun createEmbedding(@RequestBody messageRequestDto: MessageRequestDto)
            : ResponseEntity<Any> = openAiService.createEmbedding(messageRequestDto.message)

    @PostMapping("/chat")
    fun chatCompletion(@RequestBody messageRequestDto: MessageRequestDto)
            : ResponseEntity<Any> = openAiService.chatCompletion(messageRequestDto.message)

    @PostMapping("/assistant")
    fun createAssistant(@RequestBody assistantCreateRequestDto: AssistantCreateRequestDto)
            : ResponseEntity<Any> = assistantService.createAssistant(assistantCreateRequestDto)
}