package com.openai.controller

import com.openai.model.dto.AssistantCreateRequestDto
import com.openai.model.dto.CreateMessageRequestDto
import com.openai.model.dto.CreateRunsRequestDto
import com.openai.service.AssistantService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class AssistantController(
        private val assistantService: AssistantService
) {
    @PostMapping("/assistant")
    fun createAssistant(@RequestBody assistantCreateRequestDto: AssistantCreateRequestDto)
            : ResponseEntity<Any> = assistantService.createAssistant(assistantCreateRequestDto)

    @PostMapping("/threads")
    fun createThreads()
            : ResponseEntity<Any> = assistantService.createThreads()

    @DeleteMapping("/threads/{threadId}")
    fun createThreads(@PathVariable threadId: String)
            : ResponseEntity<Any> = assistantService.deleteThreads(threadId)

    @PostMapping("/messages/{threadId}")
    fun createMessages(@PathVariable threadId: String, @RequestBody createMessageRequestDto: CreateMessageRequestDto)
            : ResponseEntity<Any> = assistantService.createMessages(threadId, createMessageRequestDto)

    @GetMapping("/messages/list")
    fun getMessagesList(threadId: String)
            : ResponseEntity<Any> = assistantService.getMessagesList(threadId)

    @PostMapping("/runs/{threadId}")
    fun createRuns(@PathVariable threadId: String, @RequestBody createRunsRequestDto: CreateRunsRequestDto)
            : ResponseEntity<Any> = assistantService.createRuns(threadId, createRunsRequestDto)
}