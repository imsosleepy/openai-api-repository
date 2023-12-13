package com.openai.controller


import com.openai.model.dto.FileRequestDto
import com.openai.model.dto.MessageRequestDto
import com.openai.service.OpenAiService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OpenAiController(
        private val openAiService: OpenAiService
) {
    @PostMapping("/embedding")
    fun createEmbedding(@RequestBody messageRequestDto: MessageRequestDto)
            : ResponseEntity<Any> = openAiService.createEmbedding(messageRequestDto.message)

    @PostMapping("/upload/file")
    fun uploadFile(@ModelAttribute fileRequestDto: FileRequestDto)
            : ResponseEntity<Any> = openAiService.uploadFile(fileRequestDto.file)

    @PostMapping("/chat")
    fun chatCompletion(@RequestBody messageRequestDto: MessageRequestDto)
            : ResponseEntity<Any> = openAiService.chatCompletion(messageRequestDto.message)
}