package com.openai.service

import com.openai.client.OpenAiFeignClient
import com.openai.model.dto.openai.AssistantCreateRequestDto
import com.openai.model.dto.openai.AssistantsRequestDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AssistantService(
        private val openAiFeignClient: OpenAiFeignClient
) {
    private val assistantModel : String = "gpt-3.5-turbo-1106"

    fun createAssistant(assistantCreateRequestDto: AssistantCreateRequestDto): ResponseEntity<Any> {
        return try {
            val res = openAiFeignClient.createAssistants(
                    "assistants=v1",
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
}