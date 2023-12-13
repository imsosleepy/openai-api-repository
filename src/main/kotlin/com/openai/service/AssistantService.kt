package com.openai.service

import com.openai.client.AssistantsClient
import com.openai.model.dto.AssistantCreateRequestDto
import com.openai.model.dto.CreateMessageRequestDto
import com.openai.model.dto.CreateRunsRequestDto
import com.openai.model.dto.openai.AssistantsRequestDto
import com.openai.model.dto.openai.MessagesRequestDto
import com.openai.model.dto.openai.RunsRequestDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AssistantService(
        private val assistantsClient: AssistantsClient
) {
    private val assistantModel: String = "gpt-3.5-turbo-1106"

    fun createAssistant(assistantCreateRequestDto: AssistantCreateRequestDto): ResponseEntity<Any> {
        return try {
            val res = assistantsClient.createAssistants(
                    AssistantsRequestDto(
                            model = assistantModel,
                            name = assistantCreateRequestDto.name,
                            instructions = assistantCreateRequestDto.instruction,
                    )
            )
            ResponseEntity.ok(res)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun createThreads(): ResponseEntity<Any> {
        return try {
            val res = assistantsClient.createThreads()
            ResponseEntity.ok(res)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun deleteThreads(threadId: String): ResponseEntity<Any> {
        return try {
            val res = assistantsClient.deleteThreads(threadId)
            ResponseEntity.ok(res)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun createMessages(threadId: String, createMessageRequestDto: CreateMessageRequestDto): ResponseEntity<Any> {
        return try {
            val res = assistantsClient.createMessages(
                    threadId,
                    MessagesRequestDto(
                            role = createMessageRequestDto.role,
                            content = createMessageRequestDto.content
                    ),
            )
            ResponseEntity.ok(res)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun getMessagesList(threadId: String): ResponseEntity<Any> {
        return try {
            val res = assistantsClient.getMessagesList(threadId)
            ResponseEntity.ok(res)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    fun createRuns(threadId: String, createRunsRequestDto: CreateRunsRequestDto): ResponseEntity<Any> {
        return try {
            val res = assistantsClient.createRuns(
                    threadId,
                    RunsRequestDto(assistantId = createRunsRequestDto.assistantId),
            )
            ResponseEntity.ok(res)
        } catch (e: Exception) {
            ResponseEntity(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}