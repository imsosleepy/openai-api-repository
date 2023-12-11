package com.openai.client

import com.openai.client.header.AssistantHeaderConfiguration
import com.openai.model.dto.openai.*
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@FeignClient(name = "OpenAiAssistantsClient", url = "https://api.openai.com/v1", configuration = [AssistantHeaderConfiguration::class])
interface AssistantsClient {
    @PostMapping("/assistants")
    fun createAssistants(@RequestBody assistantsRequestDto: AssistantsRequestDto): AssistantResponseDto?

    @PostMapping("/threads")
    fun createThreads(): ThreadsResponseDto?

    @DeleteMapping("/threads/{threadId}")
    fun deleteThreads(@PathVariable threadId: String): ThreadsDeleteResponseDto?

    @PostMapping("/threads/{threadId}/messages")
    fun createMessages(@PathVariable threadId: String, @RequestBody messagesRequestDto: MessagesRequestDto): MessagesResponseDto?

    @GetMapping("/threads/{threadId}/messages")
    fun getMessagesList(@PathVariable threadId: String): MessagesListResponseDto?

    @PostMapping("/threads/{threadId}/runs")
    fun createRuns(@PathVariable threadId: String, @RequestBody runsRequestDto: RunsRequestDto): RunsResponseDto?

}