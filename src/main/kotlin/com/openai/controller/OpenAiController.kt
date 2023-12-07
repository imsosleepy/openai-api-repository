package com.openai.controller

import com.openai.service.OpenAiService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OpenAiController (
        private val openAiService: OpenAiService
){
    @PostMapping("/embedding")
    fun createEmbedding(@RequestBody message: String) : ResponseEntity<Any> = openAiService.createEmbedding(message)
}